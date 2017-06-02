package com.goldskyer.tquant.storage.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.goldskyer.tquant.storage.enums.BoardType;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentStatus;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.enums.SpecialType;

/**
 * 合约
 * @author jintianfan
 *
 */
@Entity
@Table(name = "INSTRUMENT")
public class Instrument
{

	/**
	 * 股票代码，独特的标识符。应以'.XSHG'或'.XSHE'结尾，前者代表上证，后者代表深证
	 * 样例：000001.XSHE
	 */
	@Id
	public String sysCode;

	private String symbol;//股票简称，例如'平安银行'

	/**
	 * 暂时无意义
	 * 证券的名称缩写。例如：'PAYH'就是平安银行股票的证券名缩写
	 */
	private String abbrevSymbol;
	@Enumerated(EnumType.STRING)
	private Exchange exchange;//交易所名称

	@Enumerated(EnumType.STRING)
	private InstrumentType type; //合约类型
	@Enumerated(EnumType.STRING)
	private BoardType boardType;//板块类型，主板or创业板等


	public Date listedDate;//上市日期，暂时无意义

	public Date deListedDate;//退市日期，暂时无意义
	@Enumerated(EnumType.STRING)
	private InstrumentStatus status; //初次导入默认全标记为有效
	@Enumerated(EnumType.STRING)
	private SpecialType specialType; //特殊处理类型

	public String getSysCode()
	{
		return sysCode;
	}

	public void setSysCode(String sysCode)
	{
		this.sysCode = sysCode;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	public String getAbbrevSymbol()
	{
		return abbrevSymbol;
	}

	public void setAbbrevSymbol(String abbrevSymbol)
	{
		this.abbrevSymbol = abbrevSymbol;
	}

	public Exchange getExchange()
	{
		return exchange;
	}

	public void setExchange(Exchange exchange)
	{
		this.exchange = exchange;
	}



	public InstrumentType getType()
	{
		return type;
	}

	public void setType(InstrumentType type)
	{
		this.type = type;
	}

	public BoardType getBoardType()
	{
		return boardType;
	}

	public void setBoardType(BoardType boardType)
	{
		this.boardType = boardType;
	}

	public Date getListedDate()
	{
		return listedDate;
	}

	public void setListedDate(Date listedDate)
	{
		this.listedDate = listedDate;
	}

	public Date getDeListedDate()
	{
		return deListedDate;
	}

	public void setDeListedDate(Date deListedDate)
	{
		this.deListedDate = deListedDate;
	}

	public InstrumentStatus getStatus()
	{
		return status;
	}

	public void setStatus(InstrumentStatus status)
	{
		this.status = status;
	}

	public SpecialType getSpecialType()
	{
		return specialType;
	}

	public void setSpecialType(SpecialType specialType)
	{
		this.specialType = specialType;
	}

}

