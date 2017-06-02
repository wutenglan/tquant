package com.goldskyer.tquant.storage.compute.vo;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldskyer.tquant.storage.sina.dto.SinaDataFrame;

public class SDFPair
{
	private static final Log log = LogFactory.getLog(SDFPair.class);
	private SinaDataFrame cur;
	private SinaDataFrame pre;

	public SDFPair()
	{
		super();
	}
	public SDFPair(SinaDataFrame cur, SinaDataFrame pre)
	{
		super();
		this.cur = cur;
		this.pre = pre;
		if (cur.getOpen().compareTo(BigDecimal.ZERO) == 0)
		{
			return; //无论
		}
		if (pre.getOpen().compareTo(BigDecimal.ZERO) == 0)
		{
			return; //无论
		}
		try
		{
			this.amountDiff = cur.getAmount().subtract(pre.getAmount()).floatValue();

			this.riseRate = (cur.getClose().subtract(pre.getClose())).multiply(new BigDecimal(100)).floatValue()
					/ cur.getOpen().floatValue();
			this.turnoverRate = (this.amountDiff * 100) / cur.getMarketCap().floatValue();
		}
		catch (Exception e)
		{
			log.info(this.toString() + ",e:" + e.getMessage(), e);
			e.printStackTrace();
		}


	}

	/**
	 * 涨速
	 * （cur.c-pre.c）/cur.o*100
	 */
	private float riseRate = 0;

	/**
	 * 单位时间资金流入
	 */
	private float amountDiff = 0;

	/**
	 * 流入资金与总资金的占比
	 */
	private float amountRate = 0;//需要计算总资金

	/**
	 * 换手率排行
	 */
	private float turnoverRate = 0;


	public SinaDataFrame getCur()
	{
		return cur;
	}

	public void setCur(SinaDataFrame cur)
	{
		this.cur = cur;
	}

	public SinaDataFrame getPre()
	{
		return pre;
	}

	public void setPre(SinaDataFrame pre)
	{
		this.pre = pre;
	}

	public float getRiseRate()
	{
		return riseRate;
	}

	public void setRiseRate(float riseRate)
	{
		this.riseRate = riseRate;
	}



	public float getAmountDiff()
	{
		return amountDiff;
	}

	public void setAmountDiff(float amountDiff)
	{
		this.amountDiff = amountDiff;
	}

	public float getAmountRate()
	{
		return amountRate;
	}

	public void setAmountRate(float amountRate)
	{
		this.amountRate = amountRate;
	}

	public float getTurnoverRate()
	{
		return turnoverRate;
	}

	public void setTurnoverRate(float turnoverRate)
	{
		this.turnoverRate = turnoverRate;
	}

	public String printLine()
	{
		return getCur().getSysCode() + "," + riseRate + "," + amountRate + "," + amountDiff + "," + turnoverRate;
	}

	@Override
	public String toString()
	{
		return "SDFPair [cur=" + cur + ", pre=" + pre + ", riseRate=" + riseRate + ", amountDiff=" + amountDiff
				+ ", amountRate=" + amountRate + ", turnoverRate=" + turnoverRate + "]";
	}

	/**
	 * 是否涨停
	 * @return
	 */
	public boolean isRiseLimit()
	{
		if(cur!=null && pre!=null &&cur.getVolume()>0)
		{
			if (cur.getClose().compareTo(
					cur.getPreClose().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0) //现在涨停
			{
				if (pre.getClose().compareTo(
						cur.getPreClose().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_DOWN)) < 0)//前一个节点没涨停
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否涨停板打开
	 * @return
	 */
	public boolean isRiseLimitOpen()
	{
		if (cur != null && pre != null && cur.getVolume() > 0)
		{
			if (pre.getClose().compareTo(
					pre.getPreClose().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_DOWN)) == 0) //现在涨停
			{
				if (cur.getClose().compareTo(
						pre.getPreClose().multiply(new BigDecimal(1.1)).setScale(2, BigDecimal.ROUND_HALF_DOWN)) < 0)//前一个节点没涨停
				{
					return true;
				}
			}
		}
		return false;
	}

}
