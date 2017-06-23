package com.goldskyer.tquant.storage.compress;

import com.goldskyer.tquant.storage.entities.DataFrame;

/**
 * 分时线压缩算法
 * @author jintianfan
 *
 */
public interface DataFrameCompress<T extends DataFrame>
{
	public byte[] compressDataFrame(T dataFrame);


	public  T depressDataFrame(byte[] line,Class<T> cls);



}
