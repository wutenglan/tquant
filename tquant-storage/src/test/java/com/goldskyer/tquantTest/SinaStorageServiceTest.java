package com.goldskyer.tquantTest;


import org.junit.Test;

import com.goldskyer.tquant.storage.service.ScsStorageService;
import com.goldskyer.tquant.storage.service.impl.ScsStorageServiceImpl;

public class SinaStorageServiceTest
{

	@Test
	public void testPutObject()
	{
		ScsStorageService sinaStorageService = new ScsStorageServiceImpl();
		sinaStorageService.putObject("real/three.txt", "AAAABBBBAB");
	}

}
