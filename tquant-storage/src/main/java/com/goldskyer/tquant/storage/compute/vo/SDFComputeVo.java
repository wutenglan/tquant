package com.goldskyer.tquant.storage.compute.vo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.goldskyer.tquant.common.util.DateUtil;
import com.goldskyer.tquant.common.util.FileUtil;
import com.goldskyer.tquant.storage.compute.comparator.SDFAmountDiffComparator;
import com.goldskyer.tquant.storage.compute.comparator.SDFAmountRateComparator;
import com.goldskyer.tquant.storage.compute.comparator.SDFRiseRateComparator;
import com.goldskyer.tquant.storage.compute.comparator.SDFTurnoverRateComparator;
import com.goldskyer.tquant.storage.constant.PathConstant;

/**
 * 新浪数据帧计算器
 * 描述：含有sina两个tick之间的数据
 * @author jintianfan
 *
 */
public class SDFComputeVo
{

	private static final String SPLIT_LINE = "------------------------------------------------------";
	private List<SDFPair> sdfPairs = new ArrayList<>();

	private float amountDiff;//交易金额差额

	private int tickId;//当前时间的tickID

	private int preTickId;//前一个帧的ID

	private String time;

	private List<String> resultLines = new ArrayList<>();

	private String outputFile;//输出文件路径

	public SDFComputeVo(List<SDFPair> sdfPairs, int tickId, String time, int preTickId)
	{
		super();
		this.sdfPairs = sdfPairs;
		this.tickId = tickId;
		this.time = time;
		this.preTickId = preTickId;
		this.outputFile = PathConstant.HOME + "/compute/sdf/" + DateUtil.date2String(new Date()) + ".txt";
		init();
	}

	/**
	 * 执行ComputeVo的初始化
	 */
	private void init()
	{
		/**
		 * 计算帧之间的总交易金额差额
		 */
		float amount = 0f;
		for (SDFPair pair : sdfPairs)
		{
			amount += pair.getAmountDiff();
		}
		this.amountDiff = amount;
		//计算SDFPair中的amountRate
		for (SDFPair pair : sdfPairs)
		{
			pair.setAmountRate(pair.getAmountDiff() / this.amountDiff);
		}
	}

	public void sortAndPrint()
	{
		printLine("tickId:" + tickId + ",time:" + time + ",tickRange:" + (tickId - preTickId));
		Collections.sort(sdfPairs, new SDFRiseRateComparator());
		printLine("TOP RiseRate。涨速榜");
		printTop(10);
		Collections.sort(sdfPairs, new SDFAmountDiffComparator());
		printLine("TOP AmountDiff 资金流入榜");
		printTop(10);
		Collections.sort(sdfPairs, new SDFAmountRateComparator());
		printLine("TOP AmountRate。资金流入占总资金比例的榜单");
		printTop(10);
		Collections.sort(sdfPairs, new SDFTurnoverRateComparator());
		printLine("TOP TurnoverRate。换手率榜单");
		printTop(10);
		printLine(SPLIT_LINE);
	}

	public void printTop(int n)
	{
		if (n > sdfPairs.size())
		{
			n = sdfPairs.size();
		}
		for (int i = 0; i < n; i++)
		{
			printLine(sdfPairs.get(i).printLine());
		}
	}

	private void printLine(String line)
	{
		resultLines.add(line + "\r\n");
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		List<String> aList = new ArrayList<>();
		aList.add("aa\r\n");
		aList.add("bb\r\n");

		IOUtils.writeLines(aList, "", new FileOutputStream("/data/tmp01", true));
	}

	public void writeToFile()
	{
		try
		{
			FileUtil.mkdir(FileUtil.getDirectory(outputFile));
			IOUtils.writeLines(resultLines, "", new FileOutputStream(outputFile, true));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPreTickId()
	{
		return preTickId;
	}

	public void setPreTickId(int preTickId)
	{
		this.preTickId = preTickId;
	}

	public List<SDFPair> getSdfPairs()
	{
		return sdfPairs;
	}

	public String getTime()
	{
		return time;
	}

}
