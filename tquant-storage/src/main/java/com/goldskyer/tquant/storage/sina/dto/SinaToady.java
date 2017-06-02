package com.goldskyer.tquant.storage.sina.dto;

import java.util.Date;
import java.util.List;

import com.goldskyer.tquant.storage.entities.Instrument;


public class SinaToady {
	private List<Instrument> stockVos;
	private List<SinaDataFrame> sinaDataFrames;
	private int count;
	private String sign;
	private String dateString;
	private Date date;

	public List<Instrument> getStockVos()
	{
		return stockVos;
	}

	public void setStockVos(List<Instrument> stockVos)
	{
		this.stockVos = stockVos;
	}

	public List<SinaDataFrame> getSinaDataFrames()
	{
		return sinaDataFrames;
	}

	public void setSinaDataFrames(List<SinaDataFrame> sinaDataFrames)
	{
		this.sinaDataFrames = sinaDataFrames;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String getDateString()
	{
		return dateString;
	}

	public void setDateString(String dateString)
	{
		this.dateString = dateString;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	
	

}
