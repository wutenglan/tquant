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
import com.goldskyer.tquant.storage.service.StorageFileService;

@Service
public class StorageFileServiceImpl  implements StorageFileService {

	private static final Log log = LogFactory.getLog(StorageFileServiceImpl.class);

	public String getStorageFilePath(String dateString, String time)
	{
		return PathConstant.HOME + "/tick/stocks/" + dateString + "/" + time + ".txt";
	}
	public List<DataFrame> loadFile(String dateString, String time) {
		String filePath = getStorageFilePath(dateString, time);
		if (new File(filePath).exists()) {
			try {
				List<DataFrame> sdfs = new ArrayList<>();
				DataFrameCompress dataFrameCompress = new DefaultDataFrameCompressor();
				List<String> lines=IOUtils.readLines(new FileInputStream(filePath));
				for(String line:lines)
				{
					if(StringUtils.isNotBlank(line))
					{
						DataFrame df=dataFrameCompress.depressDataFrame(line.getBytes());
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

	public List<DataFrame> getExistDataFrames(String dateString,String time)
	{
		int tickId=TickIndexCache.getTickIdByTime(time);
		int maxId=tickId+StorageConstant.FIND_TICK_VALID_RANGE;
		for(int i=tickId;i<maxId;i++)
		{
			String t=TickIndexCache.getTimeByTickId(i);
			String file=getStorageFilePath(dateString,t);
			if(new File(file).exists())
			{
				return loadFile( dateString,t);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		List<DataFrame> sds=new StorageFileServiceImpl().getExistDataFrames("20170621", "150030");
		System.out.println(sds.size());
	}
	
}
