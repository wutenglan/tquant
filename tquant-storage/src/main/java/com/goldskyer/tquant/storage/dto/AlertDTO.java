package com.goldskyer.tquant.storage.dto;

import java.util.Date;

public class AlertDTO
{
	private String sysCode;
	private float score; //报警评分，可选
	private String alertType;//报警类型：RiseLimit，RiseLimitOpen
	private String time;//tickId

	private Date putTime;//用于短路

	public String getSysCode()
	{
		return sysCode;
	}

	public void setSysCode(String sysCode)
	{
		this.sysCode = sysCode;
	}

	public float getScore()
	{
		return score;
	}

	public void setScore(float score)
	{
		this.score = score;
	}

	public String getAlertType()
	{
		return alertType;
	}

	public void setAlertType(String alertType)
	{
		this.alertType = alertType;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public Date getPutTime()
	{
		return putTime;
	}

	public void setPutTime(Date putTime)
	{
		this.putTime = putTime;
	}

}
