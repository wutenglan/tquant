package com.goldskyer.tquant.storage.compute.comparator;

import java.util.Comparator;

import com.goldskyer.tquant.storage.compute.vo.SDFPair;

/**
 * 按照涨速
 * @author jintianfan
 *
 */
public class SDFRiseRateComparator implements Comparator<SDFPair>
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
		if (p1.getRiseRate() < p2.getRiseRate())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	

}
