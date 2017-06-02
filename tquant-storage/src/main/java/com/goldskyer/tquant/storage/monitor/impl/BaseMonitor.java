package com.goldskyer.tquant.storage.monitor.impl;

import com.goldskyer.tquant.storage.cache.TickIndexCache;
import com.goldskyer.tquant.storage.cache.TickMonitorVoCache;
import com.goldskyer.tquant.storage.compute.builder.SDFComputeVoBuilder;
import com.goldskyer.tquant.storage.compute.vo.SDFComputeVo;
import com.goldskyer.tquant.storage.monitor.vo.TickMonitorVo;

public class BaseMonitor
{

	protected SDFComputeVo buildSDFComputeVo(int tickId, int period)
	{
		int preDataTickId = TickIndexCache.getPreDataTickId(tickId, period);
		TickMonitorVo preVo = TickMonitorVoCache.get("" + preDataTickId);
		TickMonitorVo curVo = TickMonitorVoCache.get("" + tickId);
		return SDFComputeVoBuilder.builder(curVo, preVo);
	}
}
