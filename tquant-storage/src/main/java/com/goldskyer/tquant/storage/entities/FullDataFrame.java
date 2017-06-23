package com.goldskyer.tquant.storage.entities;

import java.math.BigDecimal;

public class FullDataFrame extends DataFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2606975191907415857L;
	private BigDecimal preClose;//前一个线的收盘

	public BigDecimal getPreClose() {
		return preClose;
	}

	public void setPreClose(BigDecimal preClose) {
		this.preClose = preClose;
	}

	
	
}
