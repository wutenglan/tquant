package com.goldskyer.tquant.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateUtil {
	public static boolean validDateYYYYMMDD(String dateString){
		if(dateString==null)
			return false;
		try {
			if(dateString.length()!=8){
				return false;
			}
			int year = Integer.valueOf(dateString.substring(0,4));
			if(year<1990||year>2100){
				return false;
			}
			int month = Integer.valueOf(dateString.substring(4,6));
			if(month<1 || month>12){
				return false;
			}
			int day = Integer.valueOf(dateString.substring(6,8));
			if(day<1 || day>31){
				return false;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean isNumber(String number) {
		String regEx = "^[0-9]*$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(number);
		return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(validDateYYYYMMDD("*2014013"));
	}
}
