package com.goldskyer.tquant.storage.sina.dto;

import java.math.BigDecimal;

import com.goldskyer.tquant.storage.entities.DataFrame;

public class SinaDataFrame extends DataFrame
{
	private Double turnoverRatio; //换手率

	private BigDecimal preClose;

	private Double pe;

	private Double pb; //

	private Double totalCap;//总市值
	private Double marketCap; //流通市值(元)

	public Double getTurnoverRatio()
	{
		return turnoverRatio;
	}

	public void setTurnoverRatio(Double turnoverRatio)
	{
		this.turnoverRatio = turnoverRatio;
	}

	public BigDecimal getPreClose()
	{
		return preClose;
	}

	public void setPreClose(BigDecimal preClose)
	{
		this.preClose = preClose;
	}

	public Double getPe()
	{
		return pe;
	}

	public void setPe(Double pe)
	{
		this.pe = pe;
	}

	public Double getPb()
	{
		return pb;
	}

	public void setPb(Double pb)
	{
		this.pb = pb;
	}

	public Double getTotalCap()
	{
		return totalCap;
	}

	public void setTotalCap(Double totalCap)
	{
		this.totalCap = totalCap;
	}

	public Double getMarketCap()
	{
		return marketCap;
	}

	public void setMarketCap(Double marketCap)
	{
		this.marketCap = marketCap;
	}

	public float getRiseRate()
	{
		return this.getClose().subtract(this.getPreClose()).floatValue() * 100 / (this.getPreClose().floatValue());
	}

	public boolean isRiseLimit()
	{
		if (this.getVolume() == 0)
		{
			return false;
		}
		if (this.getClose().compareTo(
				this.getPreClose().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0)
		{
			return true;
		}
		return false;
	}

}
