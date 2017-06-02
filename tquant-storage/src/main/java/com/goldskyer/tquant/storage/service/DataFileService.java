package com.goldskyer.tquant.storage.service;

import com.goldskyer.tquant.storage.entities.DataFile;
import com.goldskyer.tquant.storage.vo.DataFileQueryVo;

public interface DataFileService
{
	public DataFile queryDataFile(DataFileQueryVo queryVo);

	public void addDataFile(DataFile dataFile);
}
