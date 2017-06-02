package com.goldskyer.tquant.storage.compress;

import com.goldskyer.tquant.storage.entities.DataFrame;

/**
 * 分时线压缩算法
 * @author jintianfan
 *
 */
public interface DataFrameCompress
{
	public byte[] compressDataFrame(DataFrame dataFrame);

	public DataFrame depressDataFrame(byte[] line);

}
