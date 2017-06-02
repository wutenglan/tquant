package com.goldskyer.tquant.storage.enums;

/**
 * 合约类型
 * 描述：合约类型，目前支持的类型有: 'CS', 'INDEX', 'LOF', 'ETF', 'FenjiMu', 'FenjiA', 'FenjiB', 'Future'
 * @author jintianfan
 *
 */
public enum InstrumentType
{
	/**
	 * Common Stock, 即股票
	 */
	CS,
	/**
	 * Exchange Traded Fund, 即交易所交易基金
	 */
	ETF,
	/**
	 * Listed Open-Ended Fund，即上市型开放式基金
	 */
	LOF,
	/**
	 * Fenji Mu Fund, 即分级母基金
	 */
	FENJI_MU,
	/**
	 * Fenji A Fund, 即分级A类基金
	 */
	FENJI_A,
	/**
	 * 	Fenji B Funds, 即分级B类基金
	 */
	FENGJI_B,
	/**
	 * Index, 即指数
	 */
	INDEX,
	/**
	 * Futures，即期货，包含股指、国债和商品期货
	 */
	FUTURES;
}
