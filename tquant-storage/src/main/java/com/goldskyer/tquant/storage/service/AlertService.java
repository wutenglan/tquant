package com.goldskyer.tquant.storage.service;

import com.goldskyer.tquant.storage.dto.AlertDTO;

public interface AlertService
{
	public void fireAlert(AlertDTO alertDTO);

	public void alertLogImportant(AlertDTO alertDTO);

}
