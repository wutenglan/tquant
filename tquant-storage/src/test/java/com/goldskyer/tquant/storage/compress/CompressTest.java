package com.goldskyer.tquant.storage.compress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.goldskyer.tquant.base.BaseTest;
import com.goldskyer.tquant.common.util.BytesUtil;
import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.sina.dto.SinaToady;
import com.goldskyer.tquant.storage.sina.port.MarketACurrentDataPort;

/**
 * 说明：
 * 最大整形：2147483647，最大换手：99569100
 * 最大成交金额：3600469945.00
 * @author jintianfan
 *
 */
public class CompressTest extends BaseTest
{
	/**
	 * 如果一条记录23字节，每秒采集一个tick数据（3000只股票），文件大小在1G。单一一次采集大小在69k
	 * 
	 * 未压缩的文件为197k,压缩可变45k
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test
	public void testCOmpress() throws FileNotFoundException, IOException
	{
		List<String> lines = new ArrayList<>();
		SinaToady sinaToady = MarketACurrentDataPort.getCurrentData();
		int i=0;
		for (DataFrame dataFrame : sinaToady.getSinaDataFrames())
		{
			i++;
			IOUtils.write(convert(dataFrame, i), new FileOutputStream("/data/stock/sina_comp1_min", true));
			String line = i + "," + dataFrame.getOpen().subtract(dataFrame.getLow()) + ","
					+ dataFrame.getClose().subtract(dataFrame.getLow()) + ","
					+ dataFrame.getHigh().subtract(dataFrame.getLow()) + "," + dataFrame.getLow() + ","
					+ dataFrame.getVolume() + "," + dataFrame.getAmount() + "\r\n";
			lines.add(line);

		}
		
		IOUtils.writeLines(lines, "", new FileOutputStream("/data/stock/sina.txt"));
	}

	public static byte[] convert(DataFrame dataFrame, int i)
	{
		byte[] frameBytes = new byte[18];
		byte[] openBytes = BytesUtil.intToBytes(
				(dataFrame.getOpen().subtract(dataFrame.getLow())).multiply(new BigDecimal(100)).intValue());
		byte[] closeBytes = BytesUtil.intToBytes(
				(dataFrame.getClose().subtract(dataFrame.getLow())).multiply(new BigDecimal(100)).intValue());
		byte[] highBytes = BytesUtil.intToBytes(
				(dataFrame.getHigh().subtract(dataFrame.getLow())).multiply(new BigDecimal(100)).intValue());
		byte[] lowBytes = BytesUtil.intToBytes(dataFrame.getLow().multiply(new BigDecimal(100)).intValue());
		byte[] volumeBytes = BytesUtil.intToBytes((int) dataFrame.getVolume() / 100);
		byte[] amount = BytesUtil.intToBytes(dataFrame.getAmount().divide(new BigDecimal(100)).intValue());
		byte[] ibytes = BytesUtil.intToBytes(i);
		frameBytes[0] = ibytes[2];
		frameBytes[1] = ibytes[3];
		frameBytes[2] = openBytes[2];
		frameBytes[3] = openBytes[3];
		frameBytes[4] = closeBytes[2];
		frameBytes[5] = closeBytes[3];
		frameBytes[6] = highBytes[2];
		frameBytes[7] = highBytes[3];
		frameBytes[8] = lowBytes[2];
		frameBytes[9] = lowBytes[3];
		frameBytes[10] = volumeBytes[0];
		frameBytes[11] = volumeBytes[1];
		frameBytes[12] = volumeBytes[2];
		frameBytes[13] = volumeBytes[3];
		frameBytes[14] = amount[0];
		frameBytes[15] = amount[1];
		frameBytes[16] = amount[2];
		frameBytes[17] = amount[3];
		return frameBytes;
	}

	public static void main(String[] args)
	{

		byte[] a = BytesUtil.intToBytes(713);
		System.out.println(Integer.MAX_VALUE);
	}


}
