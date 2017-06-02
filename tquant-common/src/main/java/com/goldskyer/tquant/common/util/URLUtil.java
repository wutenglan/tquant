package com.goldskyer.tquant.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;



public class URLUtil {
	private static String THS_DAILY_HISTORY_URL = "http://qd.10jqka.com.cn/api.php?p=stock_day&info=k_%s_%s&fq=&year="; //日线历史数据接口,参数1：sh,ze;参数二：证券代码
	/**
	 * 获取网络资源并保存为文件
	 * @param url 网络资源地址
	 * @param path 保存文件路径
	 * @throws IOException 
	 */
	public static void downloadFile(String url,String path) throws IOException{
		System.out.println(url);
		URL port = new URL(url);
		InputStream in = port.openStream();
		OutputStream ops = new FileOutputStream(new File(path));
		byte[] buf = new byte[1024];
		int len = 0;
		int total = 0;
		while ((len = in.read(buf)) !=-1)
		{
			ops.write(buf, 0, len);
			total += len;
		}
		System.out.println(total);
		in.close();
		ops.close();
	}
	

}
