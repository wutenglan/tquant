package com.goldskyer.tquant.storage.monitor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldskyer.tquant.storage.compute.vo.SDFComputeVo;
import com.goldskyer.tquant.storage.compute.vo.SDFPair;
import com.goldskyer.tquant.storage.dto.AlertDTO;
import com.goldskyer.tquant.storage.monitor.TickMonitor;
import com.goldskyer.tquant.storage.service.AlertService;

/**
 * 涨停板监控
 * @author jintianfan
 *
 */
@Service
public class RiseLimitMonitor extends BaseMonitor implements TickMonitor
{
	@Autowired
	private AlertService alertService;
	@Override
	public void monitor(int tickId)
	{
		int period = 1;//设置太长会导致多个报警
		SDFComputeVo sdfComputeVo = buildSDFComputeVo(tickId, period);
		if (sdfComputeVo != null)
		{
			for(SDFPair pair:sdfComputeVo.getSdfPairs())
			{
				if (pair.isRiseLimit() || pair.isRiseLimitOpen())
				{
					AlertDTO alertDTO=new AlertDTO();
					alertDTO.setSysCode(pair.getCur().getSysCode());
					alertDTO.setTime(sdfComputeVo.getTime());
					if (pair.isRiseLimit())
						alertDTO.setAlertType("RiseLimit");
					if (pair.isRiseLimitOpen())
						alertDTO.setAlertType("RiseLimitOpen");
					alertService.fireAlert(alertDTO);

				}
			}
		}
	}

}
