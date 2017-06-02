package com.goldskyer.tquant.compute;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.goldskyer.tquant.storage.compute.comparator.SDFAmountDiffComparator;
import com.goldskyer.tquant.storage.compute.comparator.SDFAmountRateComparator;
import com.goldskyer.tquant.storage.compute.comparator.SDFRiseRateComparator;

public class ComputeA
{
	public static void main(String[] args) throws Exception
	{
		final float riseRateFlag = 5f; //涨速大于5
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		List<String> lines = IOUtils.readLines(new FileInputStream("/mnt/tquant/compute/sdf/20161027.txt"));
		List<OffLineSDFPair> pairs = new ArrayList<>();
		String time = "";
		int tickId = 0;
		for (String line : lines)
		{
			String[] items = line.split(",");
			if(items.length!=5)
			{
				if (items.length == 3)
				{
					time = items[1];
					tickId = Integer.valueOf(items[0].split(":")[1]);
				}
				continue;
			}
			OffLineSDFPair pair = new OffLineSDFPair();
			pair.tickId = tickId;
			pair.time = time;
			pair.sysCode = items[0];
			pair.setRiseRate(Float.valueOf(items[1]));
			pair.setAmountRate(Float.valueOf(items[2]));
			pair.setAmountDiff(Float.valueOf(items[3]));
			pair.setTurnoverRate(Float.valueOf(items[4]));
			pairs.add(pair);
		}
		Collections.sort(pairs, new SDFRiseRateComparator());
		Map riseRateSet = new LinkedHashMap<>();
		for (int i = 0; i < pairs.size(); i++)
		{
			if (pairs.get(i).tickId < (300 + 3600) && pairs.get(i).getRiseRate() > riseRateFlag
					&& !riseRateSet.containsKey(pairs.get(i).sysCode))
			{
				riseRateSet.put(pairs.get(i).sysCode, pairs.get(i));
			}
		}
		printMap(riseRateSet);
		//-----------------------
		List<OffLineSDFPair> riseRatePairsFilter = new ArrayList<>();
		for (OffLineSDFPair osdfp : pairs)
		{
			if (riseRateSet.containsKey(osdfp.sysCode))
			{
				riseRatePairsFilter.add(osdfp);
			}
		}

		//		for (int i = 0; i < 100; i++)
		//		{
		//			printLine(riseRatePairsFilter.get(i));
		//		}
		{
			System.out.println("-----------------------------------");
			Collections.sort(riseRatePairsFilter, new SDFAmountDiffComparator());
			Map amountdiffeSet = new LinkedHashMap<>();
			for (int i = 0; i < riseRatePairsFilter.size(); i++)
			{
				if (riseRatePairsFilter.get(i).getAmountDiff() > 0
						&& !amountdiffeSet.containsKey(riseRatePairsFilter.get(i).sysCode))
				{
					amountdiffeSet.put(riseRatePairsFilter.get(i).sysCode, riseRatePairsFilter.get(i));
				}
			}
			printMap(amountdiffeSet);
		}

		{
			System.out.println("------------------------");
			Collections.sort(riseRatePairsFilter, new SDFAmountRateComparator());
			Map amountRateSet = new LinkedHashMap<>();
			for (int i = 0; i < riseRatePairsFilter.size(); i++)
			{
				if (riseRatePairsFilter.get(i).getAmountDiff() > 0
						&& !amountRateSet.containsKey(riseRatePairsFilter.get(i).sysCode))
				{
					amountRateSet.put(riseRatePairsFilter.get(i).sysCode, riseRatePairsFilter.get(i));
				}
			}
			printMap(amountRateSet);
		}

		//		Collections.sort(pairs, new SDFAmountRateComparator());
		//		for (int i = 0; i < 5; i++)
		//		{
		//			printLine(pairs.get(i));
		//		}
		//		Collections.sort(pairs, new SDFAmountDiffComparator());
		//		for (int i = 0; i < 5; i++)
		//		{
		//			printLine(pairs.get(i));
		//		}
		//		Collections.sort(pairs, new SDFTurnoverRateComparator());
		//		for (int i = 0; i < 5; i++)
		//		{
		//			printLine(pairs.get(i));
		//		}
	}

	public static void printMap(Map<String, OffLineSDFPair> map)
	{
		for (String key : map.keySet())
		{
			printLine(map.get(key));
		}
	}

	public static String printLine(OffLineSDFPair pair)
	{
		String line = pair.time + ":" + pair.sysCode + pair.getRiseRate() + "," + pair.getAmountRate() + ","
				+ pair.getAmountDiff() + ","
				+ pair.getTurnoverRate();
		System.out.println(line);
		return line;
	}



}
