package com.goldskyer.tquant.storage.hexun.dto;

import java.util.ArrayList;
import java.util.List;

import com.goldskyer.tquant.storage.entities.DataFrame;

public class HexunHistoryData
{
	private List<DataFrame> dataFrames = new ArrayList<>(); //默认升序存储

	private String startDateStr;

	private String endDateStr;

	private String publicDateStr;

	private String sysCode;

	private String curDateStr;//当前遍历的时间
	private boolean finished = false;//查询结束标识

	public HexunHistoryData(String sysCode, String startDateStr, String endDateStr)
	{
		super();
		this.startDateStr = startDateStr;
		this.curDateStr = this.startDateStr;
		this.endDateStr = endDateStr;
		this.sysCode = sysCode;
	}

	public String getSysCode()
	{
		return sysCode;
	}

	public void setSysCode(String sysCode)
	{
		this.sysCode = sysCode;
	}

	public List<DataFrame> getDataFrames()
	{
		return dataFrames;
	}

	public void setDataFrames(List<DataFrame> dataFrames)
	{
		this.dataFrames = dataFrames;
	}

	public String getStartDateStr()
	{
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr)
	{
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr()
	{
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr)
	{
		this.endDateStr = endDateStr;
	}

	public String getPublicDateStr()
	{
		return publicDateStr;
	}

	public void setPublicDateStr(String publicDateStr)
	{
		this.publicDateStr = publicDateStr;
	}

	public boolean isFinished()
	{
		return finished;
	}

	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}

	public String getCurDateStr()
	{
		return curDateStr;
	}

	public void setCurDateStr(String curDateStr)
	{
		this.curDateStr = curDateStr;
	}

}
