package com.goldskyer.tquant.storage.compute.service.impl;

import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.compute.service.SDFComputeService;
import com.goldskyer.tquant.storage.compute.vo.SDFComputeVo;


@Service
public class SDFComputeServiceImpl implements SDFComputeService
{

	@Override
	public void compute(SDFComputeVo sdfComputeVo)
	{
		sdfComputeVo.sortAndPrint();
		sdfComputeVo.writeToFile();
	}

}
