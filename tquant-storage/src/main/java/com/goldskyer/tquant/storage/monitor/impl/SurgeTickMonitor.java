package com.goldskyer.tquant.storage.monitor.impl;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.compute.comparator.SDFRiseRateComparator;
import com.goldskyer.tquant.storage.compute.vo.SDFComputeVo;
import com.goldskyer.tquant.storage.dto.AlertDTO;
import com.goldskyer.tquant.storage.monitor.TickMonitor;
import com.goldskyer.tquant.storage.service.AlertService;

/**
 * 快速上涨（大涨）监控
 * 首先监控5分钟内，涨速最快，涨幅>6的
 * @author jintianfan
 *
 */
@Service
public class SurgeTickMonitor extends BaseMonitor implements TickMonitor
{
	private static final Log log = LogFactory.getLog(SurgeTickMonitor.class);

	@Autowired
	private AlertService alertService;

	@Override
	public void monitor(int tickId)
	{
		int period=300;
		SDFComputeVo sdfComputeVo = buildSDFComputeVo(tickId, period);
		if (sdfComputeVo != null)
		{
			sdfComputeVo.sortAndPrint();
			sdfComputeVo.writeToFile();
			//计算
			Collections.sort(sdfComputeVo.getSdfPairs(), new SDFRiseRateComparator());
			for (int i = 0; i < 5; i++)
			{
				if (sdfComputeVo.getSdfPairs().get(i).getCur().getRiseRate() >= 7
						&& !sdfComputeVo.getSdfPairs().get(i).getCur().isRiseLimit()) //快速上涨，但还没有涨停
				{
					AlertDTO alertDTO = new AlertDTO();
					alertDTO.setSysCode(sdfComputeVo.getSdfPairs().get(i).getCur().getSysCode());
					alertDTO.setTime(sdfComputeVo.getTime());
					alertDTO.setAlertType("FastNearRiseLimit");
					alertService.fireAlert(alertDTO);
				}
			}
		}
	}



}
