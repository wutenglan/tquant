package com.goldskyer.tquant.storage.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.goldskyer.tquant.storage.entities.prikey.DataFrameKey;

/**合约数据帧
 * 说明：存储的是最原始最精简的数据（不带复权信息）,二次加工后的数据，比如复权因子，换手率，通过新建另外一个表来获取
 * 
 * @author jintianfan
 *
 */
@Table(name = "data_frame")
@Entity
@IdClass(value = DataFrameKey.class)
public class DataFrame
{
	@Id
	protected String sysCode; //系统指定的股票唯一编码系统唯一） //2

	protected BigDecimal open; //2 byte

	protected BigDecimal close; //2

	protected BigDecimal high; //2

	protected BigDecimal low; //2

	protected long volume; // 单位股 4

	protected BigDecimal amount; // 单位元  4

	@Id
	protected String dateString;

	//	private double factor;//前复权因子。结合当前的价格可以得到

	public String getSysCode()
	{
		return sysCode;
	}

	public void setSysCode(String sysCode)
	{
		this.sysCode = sysCode;
	}

	public BigDecimal getOpen()
	{
		return open;
	}

	public void setOpen(BigDecimal open)
	{
		if (open != null)
			open.setScale(2, RoundingMode.HALF_UP);
		this.open = open;
	}

	public BigDecimal getClose()
	{
		return close;
	}

	public void setClose(BigDecimal close)
	{
		if (close != null)
			close.setScale(2, RoundingMode.HALF_UP);
		this.close = close;
	}

	public BigDecimal getHigh()
	{
		return high;
	}

	public void setHigh(BigDecimal high)
	{
		if (high != null)
			high.setScale(2, RoundingMode.HALF_UP);
		this.high = high;
	}

	public BigDecimal getLow()
	{
		return low;
	}

	public void setLow(BigDecimal low)
	{
		if (low != null)
			low.setScale(2, RoundingMode.HALF_UP);
		this.low = low;
	}

	public long getVolume()
	{
		return volume;
	}

	public void setVolume(long volume)
	{
		this.volume = volume;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		if (amount != null)
			amount.setScale(2, RoundingMode.HALF_UP);
		this.amount = amount;
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
	public String toString()
	{
		return "DataFrame [sysCode=" + sysCode + ", open=" + open + ", close=" + close + ", high=" + high + ", low="
				+ low + ", volume=" + volume + ", amount=" + amount + ", dateString=" + dateString + "]";
	}

	public boolean compareBaseData(DataFrame d)
	{
		if (d == null)
		{
			return false;
		}
		if (this == null)
		{
			return false;
		}
		if (this.open.compareTo(d.open) == 0 && this.close.compareTo(d.close) == 0 && this.high.compareTo(d.high) == 0
				&& this.low.compareTo(d.low) == 0 && this.volume == d.volume)
		{
			if (this.amount.subtract(d.amount).abs().compareTo(BigDecimal.ONE) <= 0) //允许金额存在一定范围的偏差
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}


}
