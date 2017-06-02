package com.goldskyer.tquant.storage.monitor.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldskyer.tquant.storage.sina.dto.SinaDataFrame;

public class TickMonitorVo
{
	private int tikcId; //按分秒9:30为1 ，10：30:3601, 15:00:14401
	private List<SinaDataFrame> dataFrames = new ArrayList<>();
	private String dateString;//yyyMMDD
	private String time; //HHmmss
	private Map<String, SinaDataFrame> dataFrameMap = new HashMap<>(); //key:sysCode,val:sdf
	public int getTikcId()
	{
		return tikcId;
	}

	public void setTikcId(int tikcId)
	{
		this.tikcId = tikcId;
	}

	public List<SinaDataFrame> getDataFrames()
	{
		return dataFrames;
	}

	/**
	 * 设置的时候同时初始化map方便存储
	 * @param dataFrames
	 */
	public void setDataFrames(List<SinaDataFrame> dataFrames)
	{
		this.dataFrames = dataFrames;
		for (SinaDataFrame sdf : dataFrames)
		{
			dataFrameMap.put(sdf.getSysCode(), sdf);
		}
	}

	public String getDateString()
	{
		return dateString;
	}

	public void setDateString(String dateString)
	{
		this.dateString = dateString;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public Map<String, SinaDataFrame> getDataFrameMap()
	{
		return dataFrameMap;
	}

	public void setDataFrameMap(Map<String, SinaDataFrame> dataFrameMap)
	{
		this.dataFrameMap = dataFrameMap;
	}
	
}
