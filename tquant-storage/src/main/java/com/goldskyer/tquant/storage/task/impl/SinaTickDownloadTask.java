package com.goldskyer.tquant.storage.task.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.common.exceptions.NetException;
import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.common.util.FileUtil;
import com.goldskyer.tquant.storage.cache.TickIndexCache;
import com.goldskyer.tquant.storage.cache.TickMonitorVoCache;
import com.goldskyer.tquant.storage.compress.DataFrameCompress;
import com.goldskyer.tquant.storage.compress.DefaultDataFrameCompressor;
import com.goldskyer.tquant.storage.constant.PathConstant;
import com.goldskyer.tquant.storage.monitor.TickMonitor;
import com.goldskyer.tquant.storage.monitor.impl.RiseLimitMonitor;
import com.goldskyer.tquant.storage.monitor.impl.SurgeTickMonitor;
import com.goldskyer.tquant.storage.monitor.vo.TickMonitorVo;
import com.goldskyer.tquant.storage.sina.dto.SinaDataFrame;
import com.goldskyer.tquant.storage.sina.dto.SinaToady;
import com.goldskyer.tquant.storage.sina.port.MarketACurrentDataPort;
import com.goldskyer.tquant.storage.task.BaseScheduleTask;
import com.goldskyer.tquant.storage.utils.SpringContextHolder;

/**
 * 分时数据下载任务
 * 描述：下载分时数据到本地(/mnt/tquant/tick/stocks/yyyMMdd/hhmmss.txt)
 * 收盘文件的tick的time固定位150100.（在时间超过此时间时候保存）
 * 文件不进行压缩，方便后期处理
 * 上传SAS平台
 * 
 * 
 * @author jintianfan
 *
 */
@Service("sinaTickDownloadTask")
public class SinaTickDownloadTask extends BaseScheduleTask
{
	private static final Log log = LogFactory.getLog(SinaTickDownloadTask.class);

	/**
	 * 设置开盘时间为 9:25-11:31)  13:00-15:01
	 * @return
	 */
	@Override
	public boolean isValidTime()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour == 10 || hour == 13 || hour == 14)
			return true;
		if (hour == 9 && (minute >= 25))
			return true;
		if (hour == 11 && minute <= 31)
			return true;
		if (hour == 15 && minute < 2) //15：01分也是要采集的
			return true;
		return false;
	}

	@Override
	public void executeInner()
	{
		FileOutputStream fos = null;
		try
		{
			SinaToady sinaToday = MarketACurrentDataPort.getCurrentData();
			DataFrameCompress dataFrameCompress = new DefaultDataFrameCompressor();

			Date date=new Date();
			String time = DateUtil.date2String(date, "HHmmss");
			if (time.compareTo("150100") > 0)//到收盘时间了
			{
				String closeFilePath = PathConstant.HOME + "/tick/stocks/" + DateUtil.date2String(date) + "/"
						+ "150100.txt";//收盘文件
				if (new File(closeFilePath).exists()) //如果存在,则退出
				{
					return;
				}
				else
				{
					time = "150100";//修改tick为最终时间，然后退出
				}
			}
			//写文件
			String filePath=PathConstant.HOME + "/tick/stocks/" + DateUtil.date2String(date) + "/"
					+ time + ".txt";
			FileUtil.mkdir(FileUtil.getDirectory(filePath));
			fos = new FileOutputStream(filePath, false);
			for (SinaDataFrame df : sinaToday.getSinaDataFrames())
			{
				fos.write(dataFrameCompress.compressDataFrame(df));
			}
			fos.flush();
			int dataTickId = TickIndexCache.getTickIdByDate(date);
			TickIndexCache.setTick(date); //设置监控用的tick数据
			TickMonitorVo monitorVo=new TickMonitorVo();
			monitorVo.setDataFrames(sinaToday.getSinaDataFrames());
			monitorVo.setDateString(DateUtil.date2String(date));
			monitorVo.setTikcId(dataTickId);
			monitorVo.setTime(DateUtil.date2String(date, "HHmmss"));
			TickMonitorVoCache.put("" + dataTickId, monitorVo);
			TickMonitor t1 = SpringContextHolder.getBean(SurgeTickMonitor.class);
			t1.monitor(dataTickId);
			TickMonitor t2 = SpringContextHolder.getBean(RiseLimitMonitor.class);
			t2.monitor(dataTickId);
		}
		catch (NetException ne)
		{
			log.error(ne.getMessage(), ne);
		}
		catch (Exception e)
		{
			log.fatal(e.getMessage(), e);
		}
		finally
		{
			try
			{
				fos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		Date date = new Date();
		String filePath = PathConstant.HOME + "/tick/stocks/" + DateUtil.date2String(date) + "/"
				+ DateUtil.date2String(date, "HHMMdd") + ".txt";
		FileUtil.mkdir(FileUtil.getDirectory(filePath));
	}
}
