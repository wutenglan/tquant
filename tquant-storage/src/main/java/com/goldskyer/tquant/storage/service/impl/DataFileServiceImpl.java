package com.goldskyer.tquant.storage.service.impl;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.entities.DataFile;
import com.goldskyer.tquant.storage.service.DataFileService;
import com.goldskyer.tquant.storage.vo.DataFileQueryVo;

@Service
public class DataFileServiceImpl extends BaseServiceImpl implements DataFileService
{

	@Override
	public DataFile queryDataFile(DataFileQueryVo queryVo)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDataFile(DataFile dataFile)
	{
		baseDao.add(dataFile);
	}


}
