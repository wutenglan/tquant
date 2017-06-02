package com.goldskyer.tquant.storage.enums;

/**
 * 板块类型
 * 描述：MAIN_BOARD：主板，GEM：创业板(Growth Enterprises Market Board)，
 * @author jintianfan
 *
 */
public enum BoardType
{
	/**
	 * 主板
	 */
	MAIN_BOARD,
	/**
	 * 创业板 300开头
	 */
	GEM,
	/**
	 * 中小版 002开头
	 */
	SMALL_BOARD;
}
