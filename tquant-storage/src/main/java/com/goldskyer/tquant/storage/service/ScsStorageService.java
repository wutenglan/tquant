package com.goldskyer.tquant.storage.service;

import com.sina.cloudstorage.services.scs.model.PutObjectResult;

public interface ScsStorageService
{
	/**
	 * 上传一个文本
	 * 说明：location目录不需要加/,否则会多出一个/的目录
	 * @param location
	 * @param objectStr
	 */
	public PutObjectResult putObject(String location, String objectStr);

}
