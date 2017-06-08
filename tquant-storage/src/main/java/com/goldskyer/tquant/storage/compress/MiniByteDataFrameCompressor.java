package com.goldskyer.tquant.storage.compress;

import com.goldskyer.tquant.common.exceptions.BusinessException;
import com.goldskyer.tquant.storage.entities.DataFrame;

/**
 * 最小字节压缩算法
 * 各个行表示：code,open-low,close-low,high-low,low,vol(股),amount(元)
 * @author jintianfan
 *
 */
public class MiniByteDataFrameCompressor implements DataFrameCompress
{

	@Override
	public byte[] compressDataFrame(DataFrame dataFrame) throws BusinessException
	{
		String line = dataFrame.getSysCode() + "," + dataFrame.getOpen().subtract(dataFrame.getLow()) + ","
				+ dataFrame.getClose().subtract(dataFrame.getLow()) + ","
				+ dataFrame.getHigh().subtract(dataFrame.getLow()) + "," + dataFrame.getLow() + ","
				+ dataFrame.getVolume() + "," + dataFrame.getAmount() + "\r\n";
		return line.getBytes();
	}

	@Override
	public DataFrame depressDataFrame(byte[] line)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
