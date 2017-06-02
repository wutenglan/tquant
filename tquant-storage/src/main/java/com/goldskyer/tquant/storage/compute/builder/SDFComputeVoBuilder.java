package com.goldskyer.tquant.storage.compute.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.storage.compute.vo.SDFComputeVo;
import com.goldskyer.tquant.storage.compute.vo.SDFPair;
import com.goldskyer.tquant.storage.monitor.vo.TickMonitorVo;
import com.goldskyer.tquant.storage.sina.dto.SinaDataFrame;

public class SDFComputeVoBuilder
{
	private static final Log log = LogFactory.getLog(SDFComputeVoBuilder.class);

	public static SDFComputeVo builder(TickMonitorVo tickMonitorVo, TickMonitorVo preTickMonitorVo)
	{
		if (preTickMonitorVo == null)
		{
			return null;
		}
		List<SDFPair> pairs = new ArrayList<>();
		for (SinaDataFrame sdf : tickMonitorVo.getDataFrames())
		{
			if (preTickMonitorVo != null && preTickMonitorVo.getDataFrameMap().containsKey(sdf.getSysCode()))
			{
				SDFPair pair = new SDFPair(sdf, preTickMonitorVo.getDataFrameMap().get(sdf.getSysCode()));
				pairs.add(pair);
			}
			else
			{
				log.fatal("缺失preTickMonitorVo。sysCode:" + sdf.getSysCode());
			}
		}
		SDFComputeVo sdfComputeVo = new SDFComputeVo(pairs, tickMonitorVo.getTikcId(), tickMonitorVo.getTime(),
				preTickMonitorVo.getTikcId());
		return sdfComputeVo;
	}
}
