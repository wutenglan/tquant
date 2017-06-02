package com.goldskyer.tquant.storage.entities.prikey;

import java.io.Serializable;

import javax.persistence.Id;

/**
 * 主键设置方法 http://blog.csdn.net/robinpipi/article/details/7655388
 * @author jintianfan
 *
 */
public class DataFrameKey implements Serializable
{
	@Id
	private String sysCode;

	@Id
	private String dateString;

	public DataFrameKey()
	{
		super();
	}

	public DataFrameKey(String sysCode, String dateString)
	{
		super();
		this.dateString = dateString;
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

	public String getDateString()
	{
		return dateString;
	}

	public void setDateString(String dateString)
	{
		this.dateString = dateString;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof DataFrameKey)
		{
			DataFrameKey key = (DataFrameKey) o;
			if (this.sysCode == key.getSysCode() && this.dateString.equals(key.getDateString()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return (this.sysCode + this.dateString).hashCode();
	}

}
