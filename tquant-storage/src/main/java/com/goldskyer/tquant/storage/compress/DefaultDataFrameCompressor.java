package com.goldskyer.tquant.storage.compress;

import java.math.BigDecimal;

import com.goldskyer.tquant.storage.entities.DataFrame;
import com.goldskyer.tquant.storage.utils.ReflectUtil;

public class DefaultDataFrameCompressor<T extends DataFrame> implements DataFrameCompress <T>
{

	/**
	 * code,o,c,h,l,v,a
	 */
	@Override
	public byte[] compressDataFrame(T dataFrame)
	{

		String line = dataFrame.getSysCode() + "," + dataFrame.getOpen().subtract(dataFrame.getLow()) + ","
				+ dataFrame.getClose().subtract(dataFrame.getLow()) + ","
				+ dataFrame.getHigh().subtract(dataFrame.getLow()) + "," + dataFrame.getLow() + ","
				+ dataFrame.getVolume() + "," + dataFrame.getAmount() + "\r\n";
		return line.getBytes();
	}

	@Override
	public  T depressDataFrame(byte[] line,Class<T> cls)
	{
		String str = new String(line);
		String[] items = str.split(",");
		T df = ReflectUtil.createInstance(cls);
		df.setLow(new BigDecimal(items[4]));
		df.setOpen(df.getLow().add(new BigDecimal(items[1])));
		df.setClose(df.getLow().add(new BigDecimal(items[2])));
		df.setHigh(df.getLow().add(new BigDecimal(items[3])));
		df.setSysCode(items[0]);
		df.setVolume(Long.valueOf(items[5]));
		df.setAmount(new BigDecimal(items[6]));
		return df;
	}


}
