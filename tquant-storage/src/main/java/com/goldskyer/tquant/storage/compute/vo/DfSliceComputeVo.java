package com.goldskyer.tquant.storage.compute.vo;

import java.math.BigDecimal;

import com.goldskyer.tquant.storage.entities.DataFrame;

/**
 * Dataframe slice computeVo
 * @author jintianfan
 *
 */
public class DfSliceComputeVo extends DataFrame
{
	private BigDecimal preClose;

	public BigDecimal getPreClose()
	{
		return preClose;
	}

	public void setPreClose(BigDecimal preClose)
	{
		this.preClose = preClose;
	}

	public DfSliceComputeVo(DataFrame df)
	{
		super();
		this.sysCode=df.getSysCode();
		this.open=df.getOpen();
		this.low=df.getLow();
		this.high = df.getHigh();
		this.close = df.getClose();
		this.amount = df.getAmount();
		this.volume = df.getVolume();
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
