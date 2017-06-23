package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.DataFrame;

public interface StorageFileService {
	public String getStorageFilePath(String dateString, String time);
	
	
	public <T extends DataFrame>  List<T> getExistDataFrames(String dateString,String time,Class<T> clas);
	
	/**
	 * 获取某日的收盘数据
	 * @param dateString yyyyMMdd
	 * @return
	 */
	public <T extends DataFrame> List<T> getCloseMarketDataFrames(String dateString,Class<T> cls);

}
