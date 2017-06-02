package com.goldskyer.tquant.compute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.goldskyer.tquant.storage.compress.DFFileCompress;
import com.goldskyer.tquant.storage.compress.DefaultDataFrameCompressor;
import com.goldskyer.tquant.storage.compute.vo.DfSliceComputeVo;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.utils.ComputeTool;

public class TickFileTest
{
	private static final Log log = LogFactory.getLog(TickFileTest.class);
	public static void main(String[] args) throws Exception, IOException
	{
		List<DataFrame> preDataFrames = DFFileCompress.depress("20161102", "150100", new DefaultDataFrameCompressor());
		Map<String, DataFrame> dfMap = ComputeTool.convert2DFMap(preDataFrames);
		List<DataFrame> curDataFrames = DFFileCompress.depress("20161103", "150100", new DefaultDataFrameCompressor());
		Assert.isTrue(preDataFrames.size() > 0 && curDataFrames.size() > 0);
		//打印出涨停的股票
		List<DfSliceComputeVo> vos=new ArrayList<>();
		for (DataFrame df : curDataFrames)
		{
			DfSliceComputeVo vo = new DfSliceComputeVo(df);
			if (dfMap.containsKey(df.getSysCode()))
			{
				vo.setPreClose(dfMap.get(df.getSysCode()).getClose());
				vos.add(vo);
			}
			else
			{
				log.fatal("找不到昨天的收盘数据，不加入统计分析。syscode：" + df.getSysCode());
			}
		}
		log.info("--------涨停----------------------");
		int cnt = 0;
		for (DfSliceComputeVo vo : vos)
		{
			if (vo.isRiseLimit())
			{
				log.info(vo.getSysCode());
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
