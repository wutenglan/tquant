package com.goldskyer.tquant.common.util;

import java.math.BigDecimal;

public class FormatUtil {
	public static String formatDouble(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(f1);
	}

}
