package com.goldskyer.tquant.storage.enums;

/**
 * 合约状态。'Active' - 正常上市, 'Delisted' - 终止上市, 'TemporarySuspended' - 暂停上市, 'PreIPO' - 发行配售期间, 'FailIPO' - 发行失败
 * @author jintianfan
 *
 */
public enum InstrumentStatus
{
	ACTIVE,DELISTED,TEMPORARY_SUSPENDED,PRE_IPO,FAIL_IPO;
}
