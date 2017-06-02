package com.goldskyer.tquant.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static final String FMT_DATE_YYYY = "yyyy";
	public static final String FMT_DATE_YYYYMMDD = "yyyy-MM-dd";
	public static final String FMT_DATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_DATE_YYYYMMDDTHHMMSS_SSSXXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String FMT_DATE_YYYYMMDD_HHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FMT_DATE_HHMMSS = "HH:mm:ss";
	public static final String FMT_DATE_HHMM = "HH:mm";
	public static final String FMT_DATE_SPECIAL = "yyyyMMdd";
	public static final String FMT_DATE_MMDD = "MM-dd";
	public static final String FMT_DATE_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String FMT_DATE_MMDD_HHMM = "MM-dd HH:mm";
	public static final String FMT_DATE_MMMDDD = "MM月dd日";
	public static final String FMT_DATE_YYYYMMDDHHMM_NEW = "yyyyMMddHHmm";
	public static final String FMT_DATE_YYYY年M月D日 = "yyyy年M月d日";
	public static final String FMT_DATE_YYYY年MM月DD日 = "yyyy年MM月dd日";
	public static final String FMT_DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String FMT_DATE_YYYY年MM月DD日HH时MM分 = "yyyy年MM月dd日HH时mm分";
	public static final String FMT_DATE_YYYYMMDDHH = "yyyyMMddHH";
	public static final String FMT_DATE_YYYYMM = "yyyyMM";
	public static final String FMT_DATE_YYYYMMDDHH_MM_SS = "yyyyMMddHH:mm:ss";

	public static Date string2Date(String dateString) {
		return string2Date(dateString, "yyyyMMdd");
	}

	/**
	 * Date转成yyyyMMdd类型时间
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyyMMdd");
	}

	public static String date2String(Date date, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		String str = fmt.format(date);
		return str;
	}

	public static Date string2Date(String dateString, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int getThisYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取当日凌晨
	 * 
	 * @return
	 */
	public static Date getToday() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return (Date) currentDate.getTime().clone();
	}

	public static Date getToday(int hour, int minute, int second) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(Calendar.HOUR_OF_DAY, hour);
		currentDate.set(Calendar.MINUTE, minute);
		currentDate.set(Calendar.SECOND, second);
		currentDate.set(Calendar.MILLISECOND, 0);
		return (Date) currentDate.getTime().clone();
	}

	/**
	 * 计算两个日期间隔的天数,不满一天按一天算.区间[]
	 * 
	 * @param startDate
	 *            (yyyyMMdd)
	 * @param endDate
	 *            (yyyyMMdd)
	 * @return
	 */
	public static Integer betweenDays(String startDateString,
			String endDateString) {
		Date startDate = string2Date(startDateString);
		Date endDate = string2Date(endDateString);
		return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24)) + 1;
	}

	/**
	 * 获取今天星期几（星期日为0，星期六为6）
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	public static int getWeek(String dateString)
	{
		return getWeek(DateUtil.string2Date(dateString));
	}

	/**
	 * 获取n天前的日期
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	
	public static Date getIntervalDate(Date date, int days)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return calendar.getTime();
	}

	public static Date getIntervalHour(Date date, int hours)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		return calendar.getTime();
	}

	public static Date getIntervalMinute(Date date, int minutes)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);

		return calendar.getTime();
	}



	/**
	 * 获取某一时间前后几天
	 * @param dateStr yyyyMMdd
	 * @param days
	 * @return
	 */
	public static String getDateStrInterval(String dateStr, int days)
	{
		return date2String(getIntervalDate(string2Date(dateStr), days));
	}

	public static boolean isAM(Date date){
		return date.getHours()<12;
	}
	
	public static void main(String[] args) {
		System.out.println(formatDate(new Date(), "HHMMdd"));
	}

	public static String formatDate(Date target, String format)
	{
		if (target == null)
		{
			return "";
		}
		return new SimpleDateFormat(format).format(target);
	}

}
