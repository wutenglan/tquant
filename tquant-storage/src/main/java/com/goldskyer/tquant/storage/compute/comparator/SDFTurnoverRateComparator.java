package com.goldskyer.tquant.storage.compute.comparator;

import java.util.Comparator;

import com.goldskyer.tquant.storage.compute.vo.SDFPair;

/**
 * 按照流入资金对比
 * @author jintianfan
 *
 */
public class SDFTurnoverRateComparator implements Comparator<SDFPair>
{

	/**
	 * null放到最前面
	 */
	@Override
	public int compare(SDFPair p1, SDFPair p2)
	{
		if (p1 == null)
		{
			return 1;
		}
		if (p2 == null)
		{
			return -1;
		}
		if (p1.getTurnoverRate() < p2.getTurnoverRate())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	

}
