package com.goldskyer.tquant.storage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.cache.TickIndexCache;
import com.goldskyer.tquant.storage.compress.DataFrameCompress;
import com.goldskyer.tquant.storage.compress.DefaultDataFrameCompressor;
import com.goldskyer.tquant.storage.constant.PathConstant;
import com.goldskyer.tquant.storage.constant.StorageConstant;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.entities.FullDataFrame;
import com.goldskyer.tquant.storage.service.StorageFileService;

@Service
public class StorageFileServiceImpl  implements StorageFileService {

	private static final Log log = LogFactory.getLog(StorageFileServiceImpl.class);

	public String getStorageFilePath(String dateString, String time)
	{
		return PathConstant.HOME + "/tick/stocks/" + dateString + "/" + time + ".txt";
	}
	
	public <T extends DataFrame> List<T> loadFile(String dateString, String time,Class<T> cls) {
		String filePath = getStorageFilePath(dateString, time);
		if (new File(filePath).exists()) {
			try {
				List<T> sdfs = new ArrayList<>();
				DataFrameCompress<T> dataFrameCompress = new DefaultDataFrameCompressor<T>();
				List<String> lines=IOUtils.readLines(new FileInputStream(filePath));
				for(String line:lines)
				{
					if(StringUtils.isNotBlank(line))
					{
						T df=dataFrameCompress.depressDataFrame(line.getBytes(),cls);
						sdfs.add(df);
					}
				}
				return sdfs;
			} catch (Exception e) {
				log.info(e.getMessage(), e);
				return null;
			}
			
		} else {
			return null;
		}
	}

	public <T extends DataFrame>  List<T> getExistDataFrames(String dateString,String time,Class<T> clas)
	{
		int tickId=TickIndexCache.getTickIdByTime(time);
		int maxId=tickId+StorageConstant.FIND_TICK_VALID_RANGE;
		for(int i=tickId;i<maxId;i++)
		{
			String t=TickIndexCache.getTimeByTickId(i);
			String file=getStorageFilePath(dateString,t);
			if(new File(file).exists())
			{
				return loadFile( dateString,t,clas);
			}
		}
		return new ArrayList<>();
	}
	
	public static void main(String[] args) {
		List<FullDataFrame> sds=new StorageFileServiceImpl().getExistDataFrames("20170621", "150030",FullDataFrame.class);
		System.out.println(sds.size());
	}
	@Override
	public <T extends DataFrame> List<T> getCloseMarketDataFrames(String dateString,Class<T> cls) 
	{
		return getExistDataFrames(dateString, StorageConstant.CLOSE_MARKET_RANGE_START,cls);
	}
	
}
