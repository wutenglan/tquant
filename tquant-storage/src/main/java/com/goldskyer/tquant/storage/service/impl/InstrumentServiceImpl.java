package com.goldskyer.tquant.storage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;
import com.goldskyer.tquant.storage.service.InstrumentService;

@Service
public class InstrumentServiceImpl extends BaseServiceImpl implements InstrumentService
{
	public List<Instrument> queryInstrumentsByType(Exchange exchange, InstrumentType type)
	{
		return baseDao.query("select t from Instrument t where t.exchange=? and  t.type=? ", exchange, type);
	}

	@Transactional
	public void addInstrument(Instrument instrument)
	{
		baseDao.add(instrument);
	}

	public void modifyInstrument(Instrument instrument)
	{
		baseDao.modify(instrument);
	}
}
