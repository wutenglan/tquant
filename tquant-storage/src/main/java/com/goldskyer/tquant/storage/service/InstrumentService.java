package com.goldskyer.tquant.storage.service;

import java.util.List;

import com.goldskyer.tquant.storage.entities.Instrument;
import com.goldskyer.tquant.storage.enums.Exchange;
import com.goldskyer.tquant.storage.enums.InstrumentType;

public interface InstrumentService
{
	public List<Instrument> queryInstrumentsByType(Exchange exchange, InstrumentType type);

	public void addInstrument(Instrument instrument);

	public void modifyInstrument(Instrument instrument);
}
