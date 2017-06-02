package com.goldskyer.tquant.common.util;

/**
 * 
 * @ClassName: RandTool 
 * @Description: 随机数生成工具类
 * @author hzjintianfan
 * @date 2014-7-23 下午7:25:41
 */
public class RandUtil
{

	/**
	 * 生成N位随机数字
	 * @param num
	 * @return
	 */
	public static String rand(Integer num)
	{
		String result = "";
		for (int i = 0; i < num; i++)
		{
			result += (int) (Math.random() * 10);
		}
		return result;
	}
	
	public static String generateCode(int i, int len) {
		String result = "";
		for (int j = 0; j < len - String.valueOf(i).length(); j++) {
			result += "0";
		}
		result += String.valueOf(i);
		return result;
	}

	public static void main(String[] args)
	{
		System.out.println(RandUtil.rand(1));
	}

}
