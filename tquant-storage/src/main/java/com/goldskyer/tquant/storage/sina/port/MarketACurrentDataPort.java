package com.goldskyer.tquant.storage.sina.port;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.common.exceptions.BusinessException;
import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.common.util.HttpUtil;
import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.sina.dto.SinaDataFrame;
import com.goldskyer.tquant.storage.sina.dto.SinaToady;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取当前所有股票行情数据
 * @author jintianfan
 *
 */
public class MarketACurrentDataPort
{
	private static Log log = LogFactory.getLog(MarketACurrentDataPort.class);
	public static String INSTANT_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=1&num=100&sort=symbol&asc=1&node=hs_a";
	public static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(40); 
	/**
	 * 获取当前的行情数据
	 * @return
	 */
	public static SinaToady getCurrentData()
	{
		Date date = new Date();
		final String dateString = DateUtil.date2String(date, "yyyyMMdd");
		int TOTAL_TASK=40;
        CompletionService<SinaToady> cService = new ExecutorCompletionService<SinaToady>(fixedThreadPool);


		// 向里面扔任务
		for ( int i = 1; i < 40; i++) {
			final int curPage=i;
			cService.submit(new Callable<SinaToady>() {
				@Override
				public SinaToady call() {
					try{
						String result = HttpUtil.openUrlReturnMoreMessage(INSTANT_URL.replace("page=1&", "page="+curPage+"&"), "GBK");
						SinaToady sinaToady= convertSinaToady(result, dateString);
						//log.info("下载成功"+curPage);
						return sinaToady;

					}catch(Exception e)
					{
						log.fatal(e.getMessage()+"curPage:"+curPage,e);
						return new SinaToady();
					}
					
				}
				
			});
		}
		SinaToady sinaToady = new SinaToady();
		sinaToady.setDate(new Date());
		sinaToady.setDateString(dateString);
		// 检查线程池任务执行结果
		for (int i = 1; i < TOTAL_TASK; i++) {
			try {
				Future<SinaToady> future = cService.take();
				if(future==null)
				{
					log.info("f is null");
				}
				if(future.get()!=null)
				{
					sinaToady.getSinaDataFrames().addAll(future.get().getSinaDataFrames());
				}
			} catch (Exception e) {
				log.fatal(e.getMessage(),e);
			} 
		}
		sinaToady.setCount(sinaToady.getSinaDataFrames().size());
		return sinaToady;
	}

	private static SinaToady convertSinaToady(String jsonString, String dateString)
	{
		if(!jsonString.startsWith("["))
		{
			return new SinaToady();
		}
		SinaToady sinaToady = new SinaToady();
		List<SinaDataFrame> dataFrames = new ArrayList<SinaDataFrame>();
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		sinaToady.setCount(jsonArray.size());
		sinaToady.setDate(new Date());
		sinaToady.setDateString(dateString);
		if (jsonArray != null && !jsonArray.isEmpty())
		{
			for (int j = 0; j < jsonArray.size(); j++)
			{
				JSONObject jsonObject = (JSONObject) jsonArray.get(j);
				SinaDataFrame dataFrame = new SinaDataFrame();
				dataFrame.setSysCode(convertPureCode2SysCode(jsonObject.getString("code")));
				dataFrame.setOpen(new BigDecimal(jsonObject.getString("open")));
				dataFrame.setClose(new BigDecimal(jsonObject.getString("trade")));
				dataFrame.setHigh(new BigDecimal(jsonObject.getString("high")));
				dataFrame.setLow(new BigDecimal(jsonObject.getString("low")));
				dataFrame.setAmount(new BigDecimal(jsonObject.getString("amount")));
				dataFrame.setVolume(100 * Long.valueOf(jsonObject.getString("volume")));
				dataFrame.setDateString(dateString);
				dataFrame.setTurnoverRatio(Double.valueOf(jsonObject.getString("turnoverratio")));
				dataFrame.setPreClose(new BigDecimal(jsonObject.getString("settlement")));
				dataFrame.setPe(Double.valueOf(jsonObject.getString("per")));
				dataFrame.setPb(Double.valueOf(jsonObject.getString("pb")));
				dataFrame.setTotalCap(Double.valueOf(jsonObject.getString("mktcap")) * 10000);
				dataFrame.setMarketCap(Double.valueOf(jsonObject.getString("nmc")) * 10000);
				dataFrames.add(dataFrame);
			}
		}
		sinaToady.setSinaDataFrames(dataFrames);
		return sinaToady;
	}


	private static String convertPureCode2SysCode(String code)
	{
		String prefix = code.substring(0, 3);
		if (prefix.equals("600") || prefix.equals("601") || prefix.equals("603") || prefix.equals("900"))
		{
			return code + "." + Exchange.XSHG.name();
		}
		else if (prefix.equals("000") || prefix.equals("001") || prefix.equals("002") || prefix.equals("300")
				|| prefix.equals("200"))
		{
			return code + "." + Exchange.XSHE.name();
		}
		else
		{
			throw new BusinessException("code不能映射到对应的市场。code：" + code);
		}
	}
	
	public static void main(String[] args) {
		SinaToady sinaToady=getCurrentData();
		System.out.println(sinaToady.getSinaDataFrames().size());
	}
}
