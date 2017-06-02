package com.goldskyer.tquant.common.util;

import java.math.BigDecimal;

public class MathUtil {

	public static boolean isEquals(float f1, float f2) {
		BigDecimal b1 = new BigDecimal(f1).setScale(2,
				BigDecimal.ROUND_HALF_DOWN);
		BigDecimal b2 = new BigDecimal(f2).setScale(2,
				BigDecimal.ROUND_HALF_DOWN);
		return b1.equals(b2);
	}

	/**
	 * Double数据四舍五入
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static BigDecimal round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @param round_mode
	 *            指定的舍入模式
	 * 
	 * @return 四舍五入后的结果
	 */

	public static BigDecimal round(double v, int scale, int round_mode)

	{
		if (scale < 0)

		{

			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(v);

		return b.setScale(scale, round_mode);

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果，以字符串格式返回
	 */

	public static String round(String v, int scale)

	{

		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @param round_mode
	 *            指定的舍入模式
	 * 
	 * @return 四舍五入后的结果，以字符串格式返回
	 */

	public static String round(String v, int scale, int round_mode)

	{

		if (scale < 0)

		{

			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(v);

		return b.setScale(scale, round_mode).toString();

	}

	public static void main(String[] args) {
		double a = 31.05f;
		System.out.println(MathUtil.round(
				new BigDecimal(31.05).multiply(new BigDecimal(1.1))
						.doubleValue(), 2));
	}
}
