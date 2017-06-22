package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.DataFrame;

public interface StorageFileService {
	public String getStorageFilePath(String dateString, String time);
	
	public List<DataFrame> loadFile(String dateString, String time) ;
	
	public List<DataFrame> getExistDataFrames(String dateString,String time);
}
