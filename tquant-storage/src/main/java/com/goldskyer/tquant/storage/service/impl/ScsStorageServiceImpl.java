package com.goldskyer.tquant.storage.service.impl;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.service.ScsStorageService;
import com.sina.cloudstorage.auth.AWSCredentials;
import com.sina.cloudstorage.auth.BasicAWSCredentials;
import com.sina.cloudstorage.services.scs.SCS;
import com.sina.cloudstorage.services.scs.SCSClient;
import com.sina.cloudstorage.services.scs.model.ObjectMetadata;
import com.sina.cloudstorage.services.scs.model.PutObjectResult;

/**
 * 新浪云存储服务类
 * @author jintianfan
 *
 */
@Service
public class ScsStorageServiceImpl implements ScsStorageService
{
	String bucketName = "tquant";
	String accessKey = "17v6jwfms9nRjRjWzOJb";
	String secretKey = "bef0255f60a86a579e6c350935a8c500201009c0";
	AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
	SCS conn = new SCSClient(credentials);

	/**
	 * 上传一个文本
	 * @param location
	 * @param objectStr
	 */
	public PutObjectResult putObject(String location, String objectStr)
	{
		ObjectMetadata meta= new ObjectMetadata();
		meta.setContentLength(objectStr.getBytes().length);
		return conn.putObject(bucketName, location,
				new ByteArrayInputStream(objectStr.getBytes()), meta);
	}
}
