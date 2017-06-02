package com.goldskyer.tquant.storage.compress;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.goldskyer.tquant.storage.entities.DataFrame;

public class DFFileCompress
{
	public static List<DataFrame> depress(String date, String time, DataFrameCompress compress) throws Exception
	{
		List<DataFrame> dataFrames = new ArrayList<>();
		List<String> lines = IOUtils
				.readLines(new FileInputStream("/mnt/tquant/tick/stocks/" + date + "/" + time + ".txt"));
		for (String line : lines)
		{
			dataFrames.add(compress.depressDataFrame(line.getBytes()));
		}
		return dataFrames;
	}
}
