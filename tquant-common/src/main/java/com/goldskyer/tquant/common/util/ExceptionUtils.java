package com.goldskyer.tquant.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static final String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public static void main(String[] args) {
		try{
			int i=1/0;
		}
		catch (Exception e) {
			e.printStackTrace();
			//System.out.println(ExceptionUtils.getStackTrace(e));
		}
	}
}
