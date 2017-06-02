package com.goldskyer.tquant.storage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.common.util.PingYinUtil;
import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.InstrumentStatus;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.enums.SpecialType;
import com.goldskyer.tquant.storage.service.InstrumentService;
import com.goldskyer.tquant.storage.utils.MarketCnUtil;

public class InstrumentServiceImplTest extends BaseTest
{
	@Autowired
	private InstrumentService instrumentService;

	@Test
	public void testAdd()
	{
		Instrument instrument = new Instrument();
		instrument.setSysCode("000002.XSHE");
		instrumentService.addInstrument(instrument);
	}

	/**从原有的数据库中导入股票数据
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void initInstruments() throws FileNotFoundException, IOException
	{
		List<String> lines = IOUtils.readLines(new FileInputStream(new File("/data/stock/stock.csv")));
		for (String line : lines)
		{
			String code = line.split(",")[0].trim();
			String symbol = line.split(",")[1].trim();
			Instrument instrument = new Instrument();
			instrument.setSysCode(MarketCnUtil.getSysCodeByMarketCnCode(code));
			instrument.setSymbol(getPureSymbolBySymbol(symbol));
			instrument.setAbbrevSymbol(PingYinUtil.getFirstSpell(instrument.getSymbol()));
			instrument.setExchange(MarketCnUtil.getExchangeByMarketCnCode(code));
			instrument.setBoardType(MarketCnUtil.getBoardTypeByMarketCnCode(code));
			instrument.setListedDate(null);
			instrument.setDeListedDate(null);
			instrument.setSpecialType(geSpecialTypeByMarketCnSymbol(symbol));
			instrument.setStatus(InstrumentStatus.ACTIVE);
			instrument.setType(InstrumentType.CS);//股票
			instrumentService.addInstrument(instrument);
		}

	}

	public static String getPureSymbolBySymbol(String symbol)
	{
		if (symbol.contains("N"))
		{
			return symbol.replaceAll("N", "");
		}
		else if (symbol.contains("*ST"))
		{
			return symbol.replaceAll("\\*ST", "");
		}
		if (symbol.contains("ST"))
		{
			return symbol.replaceAll("ST", "");
		}
		return symbol;
	}

	public static SpecialType geSpecialTypeByMarketCnSymbol(String symbol)
	{
		if (symbol.contains("*ST"))
		{
			return SpecialType.START_ST;
		}
		else if (symbol.contains("ST"))
		{
			return SpecialType.ST;
		}
		else
		{
			return SpecialType.NORMAL;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(geSpecialTypeByMarketCnSymbol("*STasd"));
	}
}
