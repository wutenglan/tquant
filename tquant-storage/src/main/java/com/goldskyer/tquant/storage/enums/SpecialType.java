package com.goldskyer.tquant.storage.enums;

/**
 * 特别处理状态
 * 描述：'Normal' - 正常上市, 'ST' - ST处理, 'StarST' - *ST代表该股票正在接受退市警告, 'PT' - 代表该股票连续3年收入为负，将被暂停交易, 'Other' - 其他
 * PT类股票的交易从2002年5月1日起停止。所以这里不考虑了
 * @author jintianfan
 *
 */
public enum SpecialType
{
	NORMAL, ST,
	/**
	 * *ST代表该股票正在接受退市警告
	 */
	START_ST,
	/**
	 * 代表该股票连续3年收入为负
	 */
	OTHER;
}
