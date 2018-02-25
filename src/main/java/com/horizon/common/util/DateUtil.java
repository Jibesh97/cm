package com.horizon.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

/**
 * <P>
 * FileName: DateUtil.java
 * 
 * @author peiyy
 *         <P>
 *         CreateTime: 2012-09-26
 *         <P>
 *         Description: 放置与Date有关的工具方法
 *         <P>
 *         Version:v1.0
 *         <P>
 *         History:
 */
public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	public static final String DATE_TIME_FORMAT_4SS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_TIME_FORMAT_4MM = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_4DD = "yyyy-MM-dd";

	public static final String DATE_FORMAT_YYYYMMDD = "yyyy/MM/dd";

	public static final String DATE_TIME_FORMAT_ALL = "yyyyMMddHHmmss";

	public static final String DATE_TIME_FORMAT_YMD = "yyyyMM";

	public static final String DATE_FORMAT_2DD = "yyMMdd";

	public static final String DATE_FORMAT_4YMD = "yyyyMMdd";

	public static final String DATE_FORMAT_HH24 = "yyMMddHH";

	public static final String DATE_FORMAT_4Y = "yyyy";
	/**
     * 
     */
	public static final SimpleDateFormat DATE_TIME_F = new SimpleDateFormat(
			DATE_TIME_FORMAT_4SS);

	/**
     * 
     */
	public static final SimpleDateFormat DATE_TIME_HM = new SimpleDateFormat(
			DATE_TIME_FORMAT_4MM);

	/**
     * 
     */
	public static final SimpleDateFormat DATE_F = new SimpleDateFormat(
			DATE_FORMAT_4DD);

	/**
     * 
     */
	private DateUtil() {
	}

	/**
	 * <P>
	 * Function: getSimpleDateFormat
	 * <P>
	 * Description:
	 * <P>
	 * Others:返回SimpleDateFormat
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param formate
	 * @return SimpleDateFormat
	 */
	protected static SimpleDateFormat getSimpleDateFormat(String formate) {
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		return formatdater;
	}

	/**
	 * 按指定的格式将日期对象转换为字符串
	 * <P>
	 * Function: toString
	 * <P>
	 * Description:
	 * <P>
	 * Others:返回日期字符串
	 * 
	 * @author: peiyy
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String toString(Date date, String format) {
		if (date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 按指定格式将字符串转换为日期对象
	 * <P>
	 * Function: toDate
	 * <P>
	 * Description:
	 * <P>
	 * Others:返回日期
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param dateStr
	 * @param format
	 * @return Date
	 * @throws ParseException
	 * 
	 */
	public static Date toDate(String dateStr, String format)
			throws ParseException {
		if (StringUtil.isEmpty(dateStr))
			return null;
		DateFormat df = new SimpleDateFormat(format);

		return df.parse(dateStr);

	}

	/**
	 * 按指定格式将字符串转换为日期对象
	 * <P>
	 * Function: toDate
	 * <P>
	 * Description:
	 * <P>
	 * Others:返回日期
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param dateStr
	 * @param format
	 * @return Date
	 * @throws ParseException
	 * 
	 */
	public static Date toDate(String dateStr, String format1, String format2)
			throws ParseException {
		if (StringUtil.isEmpty(dateStr))
			return null;
		try {
			DateFormat df = new SimpleDateFormat(format1);
			return df.parse(dateStr);
		} catch (ParseException e) {
			DateFormat df = new SimpleDateFormat(format2);
			return df.parse(dateStr);
		}

	}

	/**
	 * <P>
	 * Function: getSysDate
	 * <P>
	 * Description:
	 * <P>
	 * Others:返回系统时间
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param datetime
	 * @param offset
	 * @return String
	 * @throws ParseException
	 */
	public static String getDateString(String formate) throws ParseException {
		String result = null;
		Date currentDate = getDBDate();
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		result = formatdater.format(currentDate);
		return result;
	}

	/**
	 * 获得数据库当前时间的毫秒数
	 * <P>
	 * Function: getDBTime
	 * <P>
	 * Description:返回数据库时间毫秒数
	 * <P>
	 * 
	 * @author:
	 * @CreateTime: 2012-10-29
	 * @return Date
	 */
	public static long getDBTime() {
		return SysDate.getCurrentTimestamp().getTime();
	}

	/**
	 * 获得数据库当前时间
	 * <P>
	 * Function: getDBDate
	 * <P>
	 * Description:返回数据库时间
	 * <P>
	 * Others:返回日期
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @return Date
	 */
	public static Date getDBDate() {
		return SysDate.getCurrentTimestamp();
	}

	/**
	 * 获得数据库当前时间字符串 精确到秒
	 * <P>
	 * Function: getDBDateTimeString
	 * <P>
	 * Description:返回数据库时间字符串
	 * <P>
	 * Others:返回日期
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @return String
	 */
	public static String getDBDateTimeString() {
		return DateUtil.dateTime2Str(SysDate.getCurrentTimestamp());
	}

	/**
	 * 
	 * <P>
	 * Function: getCurrentDateTime
	 * <P>
	 * Description: //定义日期的格式
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param formate
	 *            转换为日期型的字符串
	 * @return String
	 */
	public static String getCurrentDateTime(String formate) {
		Date currentDate = getDBDate();
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		return formatdater.format(currentDate);
	}

	/**
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param formate
	 *            获取当前日期的后一天的日期
	 * @return String
	 */
	public static String getbeforeDate() {

		Date currentDate = getDBDate();

		Calendar c = new GregorianCalendar();
		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, 1);
		currentDate = c.getTime();

		SimpleDateFormat formatdater = getSimpleDateFormat(DATE_FORMAT_4DD);
		return formatdater.format(currentDate);
	}

	/**
	 * 
	 * @author:
	 * @CreateTime: 2012-11-20
	 * @param formate
	 *            获取DB的年份
	 * @return String 描述： 1、获取数据库时间 2、格式化为字符串 3、截取前四位就是年 4、然后转化成为整数返回
	 */
	public static int getDByear() {

		Date currentDate = getDBDate();

		SimpleDateFormat formatdater = getSimpleDateFormat(DATE_FORMAT_4DD);

		String yearStrTemp = formatdater.format(currentDate);

		int dbYear = -1;

		try {
			dbYear = Integer.parseInt(yearStrTemp.substring(0, 4));
		} catch (Exception e) {
			dbYear = -1;
		}

		return dbYear;
	}

	/**
	 * 
	 * @author:
	 * @CreateTime: 2012-11-20 获取当前日期30天前的0点0分0秒
	 * 
	 * @return Date
	 */
	public static Date getDayMonthAgo() {

		java.text.Format formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		java.util.Date theDayMonthAgo = getDBDate();

		String afterDate = formatter.format(theDayMonthAgo);

		theDayMonthAgo = DateUtil.getToDayBeginTime(afterDate);// 转成dbdate的00000

		long afterTime = (theDayMonthAgo.getTime() / 1000) - 60 * 60 * 24 * 90;

		theDayMonthAgo.setTime(afterTime * 1000);

		return theDayMonthAgo;

	}

	/**
	 * 
	 * @author:
	 * @CreateTime: 2012-11-20 获取当前数据库时间的23点59 59
	 * 
	 * @return Date
	 */
	public static Date getTodayLastTime() {

		java.text.Format formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		java.util.Date theDayMonthAgo = getDBDate();

		String afterDate = formatter.format(theDayMonthAgo);

		theDayMonthAgo = DateUtil.getToDayEndTime(afterDate);// 转成dbdate的00000

		return theDayMonthAgo;

	}

	/**
	 * 
	 * <P>
	 * Function: getYesterday
	 * <P>
	 * Description: //返回前一天的日期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @return String
	 */
	public static Date getYesterday() {
		return addDays(getDBDate(), -1);
	}

	/**
	 * 
	 * <P>
	 * Function: getYesterday
	 * <P>
	 * Description: //返回前一天日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 */
	public static String getYesterday(String formate) {
		return addDays(getDBDate(), -1, formate);
	}

	/**
	 * 
	 * <P>
	 * Function: addDays
	 * <P>
	 * Description: //返回指定多少之后的日期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 *            //日期
	 * @param offset
	 *            //相隔多少天
	 * @return Date
	 */
	public static Date addDays(Date date, int offset) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 
	 * <P>
	 * Function: addDays
	 * <P>
	 * Description: //返回指定多少天之后的日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 *            //日期
	 * @param offset
	 *            //相隔多少天
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 */
	public static String addDays(Date date, int offset, String formate) {
		if (null == date || StringUtil.isEmpty(formate)) {
			return null;
		}
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 
	 * <P>
	 * Function: addDays
	 * <P>
	 * Description: //返回指定多少天之后的日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 *            //转换为日期型的字符串
	 * @param offset
	 *            //相隔多少天
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 * @throws ParseException
	 */
	public static String addDays(String date, int offset, String formate)
			throws ParseException {
		if (null == date || StringUtil.isEmpty(formate)) {
			return null;
		}
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(date));
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 
	 * <P>
	 * Function: addHours
	 * <P>
	 * Description: //返回指定多少小时之后的日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param datetime
	 *            //转换为日期型的字符串
	 * @param offset
	 *            //相隔多少天
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 * @throws ParseException
	 */
	public static Date addHours(Date datetime, int offset)
			throws ParseException {
		if (null == datetime) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.HOUR, offset);
		return calendar.getTime();
	}

	/**
	 * 
	 * <P>
	 * Function: addHours
	 * <P>
	 * Description: //返回指定多少小时之后的日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param datetime
	 *            //转换为日期型的字符串
	 * @param offset
	 *            //相隔多少天
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 * @throws ParseException
	 */
	public static String addHours(Date datetime, int offset, String formate)
			throws ParseException {
		if (null == datetime || StringUtil.isEmpty(formate)) {
			return null;
		}
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datetime);
		calendar.add(Calendar.HOUR, offset);
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 
	 * <P>
	 * Function: addHours
	 * <P>
	 * Description: //返回指定多少小时之后的日期进行日期转换
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param datetime
	 *            //转换为日期型的字符串
	 * @param offset
	 *            //相隔多少天
	 * @param formate
	 *            //转换为日期型的字符串
	 * @return String
	 * @throws ParseException
	 */
	public static String addHours(String datetime, int offset, String formate)
			throws ParseException {
		if (null == datetime || StringUtil.isEmpty(formate)) {
			return null;
		}
		SimpleDateFormat formatdater = getSimpleDateFormat(formate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatdater.parse(datetime));
		calendar.add(Calendar.HOUR, offset);
		return formatdater.format(calendar.getTime());
	}

	/**
	 * 当前时间+指定时间间隔（秒为单位）
	 * <P>
	 * Function: addSeconds
	 * <P>
	 * Description: //返回指定多少秒之后的日期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @param offset
	 * @return java.util.Date
	 */
	public static Date addSeconds(Date date, int offset) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, offset);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的起始时间，如：2007-6-6 00:00:00
	 * <P>
	 * Function: getStartDateTime
	 * <P>
	 * Description: //返回指定日期的开始时间
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @return Date
	 */
	public static Date getStartDateTime(Date date) {
		if (null == date) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的结束时间，如：2007-6-6 23:59:59
	 * <P>
	 * Function: getEndDateTime
	 * <P>
	 * Description: //返回指定日期的结束时间
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @return Date
	 */
	public static Date getEndDateTime(Date date) {
		if (null == date) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 日期（精确到秒）转字符串
	 * <P>
	 * Function: dateTime2Str
	 * <P>
	 * Description: //返回指定日期字符串
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @return String
	 */
	public static String dateTime2Str(Date date) {
		String str = "";
		if (null != date) {
			str = DateUtil.toString(date, DATE_TIME_FORMAT_4SS);
		}
		return str;
	}

	/**
	 * 字符串转日期（精确到秒）
	 * <P>
	 * Function: str2DateTime
	 * <P>
	 * Description: //返回指定日期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param str
	 * @return
	 */
	public static Date str2DateTime(String datetime) {

		if (StringUtil.isEmpty(datetime)) {
			return null;
		}
		try {
			return DateUtil.toDate(datetime, DATE_TIME_FORMAT_4SS);
		} catch (ParseException e) {
			logger.debug("日期转换异常：" + e.getMessage());
			return null;
		}

	}

	/**
	 * 字符串转日期（精确到秒）
	 * <P>
	 * Function: str2DateTime
	 * <P>
	 * Description: //返回指定日期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param str
	 * @return
	 */
	public static Date str2Date(String date) {

		if (StringUtil.isEmpty(date)) {
			return null;
		}
		try {
			return DateUtil.toDate(date, DATE_FORMAT_4DD);
		} catch (ParseException e) {
			logger.debug("日期转换异常：" + e.getMessage());
			return null;
		}

	}

	/**
	 * 根据日期返回星期
	 * <P>
	 * Function: date2Week
	 * <P>
	 * Description: //返回指定日期的星期
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @param date
	 * @return String
	 */
	public static String date2Week(Date date) {
		if (null == date) {
			return null;
		}
		return new SimpleDateFormat("E").format(date);
	}

	/**
	 * 获取一个永久时间 Description: //返回指定的日期
	 * <P>
	 * Function: getForeverDate
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-9-26
	 * @return
	 */
	public static Date getForeverDate() {
		try {
			return toDate("2099-12-31 00:00:00", DATE_TIME_FORMAT_4SS);
		} catch (ParseException e) {
			logger.error("日期转换异常：" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 获取当天的零点零分零秒
	 * <P>
	 * Function: getToDayBeginTime
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-10-11
	 * @param startDate
	 * @return
	 */
	public static Date getToDayBeginTime(String startDate) {
		try {
			if (startDate == null || "".equals(startDate)) {
				return null;
			}
			Date d = new SimpleDateFormat(DATE_FORMAT_4DD).parse(startDate);
			Calendar currentDate = new GregorianCalendar();
			currentDate.setTime(d);
			currentDate.set(Calendar.HOUR_OF_DAY, 0);
			currentDate.set(Calendar.MINUTE, 0);
			currentDate.set(Calendar.SECOND, 0);
			return currentDate.getTime();
		} catch (ParseException e) {
			logger.error("日期转换异常：" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取当天的23时59分59秒
	 * <P>
	 * Function: getToDayBeginTime
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-10-11
	 * @param endDate
	 * @return
	 */
	public static Date getToDayEndTime(String endDate) {
		try {
			if (endDate == null || "".equals(endDate)) {
				return null;
			}
			Date d = new SimpleDateFormat(DATE_FORMAT_4DD).parse(endDate);
			Calendar currentDate = new GregorianCalendar();
			currentDate.setTime(d);
			currentDate.set(Calendar.HOUR_OF_DAY, 23);
			currentDate.set(Calendar.MINUTE, 59);
			currentDate.set(Calendar.SECOND, 59);
			return currentDate.getTime();
		} catch (ParseException e) {
			logger.error("日期转换异常：" + e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * 获取当天的零点零分零秒
	 * <P>
	 * Function: getToDayBeginTimeString
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-10-11
	 * @param startDate
	 * @return
	 */
	public static String getToDayBeginTimeString(String startDate) {
		if (startDate == null || "".equals(startDate)) {
			return null;
		}
		return startDate + " 00:00:00";
	}
	/**
	 * 获取当天的23时59分59秒
	 * <P>
	 * Function: getToDayEndTimeString
	 * <P>
	 * Others:
	 * 
	 * @author:
	 * @CreateTime: 2012-10-11
	 * @param endDate
	 * @return
	 */
	public static String getToDayEndTimeString(String endDate) {
		if (endDate == null || "".equals(endDate)) {
			return null;
		}
		return endDate + " 23:59:59";
	}

	/**
	 * 
	 * @author
	 * 
	 */
	private static class SysDate {

		private SysDate() {
		}

		/**
	     * 
	     */
		private static Logger logger = Logger.getLogger(SysDate.class);

		/**
		 * 数据库时间和应用服务器时间差
		 */
		private static Long timeInterval = null;

		static {
			if (timeInterval == null) {
				try {
					Timestamp time = getCurrentTimestampFromDB();
					java.util.Date date = new java.util.Date();
					timeInterval = new Long(time.getTime() - date.getTime());
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						logger.debug(e.getMessage());
					}
					timeInterval = new Long(0);
				}
				logger.info("数据库与服务器时间差为:" + timeInterval);
			}
		}

		/**
		 * 获取当前日期
		 * 
		 * @return java.sql.Date
		 */
		@SuppressWarnings("unused")
		private static java.sql.Date getCurrentDate() {
			return new java.sql.Date(new java.util.Date().getTime()
					+ timeInterval.longValue());
		}

		/**
		 * 获取当前时间
		 * 
		 * @return java.sql.Timestamp
		 */
		private static Timestamp getCurrentTimestamp() {
			return new java.sql.Timestamp(new java.util.Date().getTime()
					+ timeInterval.longValue());
		}

		/**
		 * 获取当前数据库日期，只能在<b>DAO</b>或<b>service</b>层调用
		 * 
		 * @return java.sql.Date
		 */
		@SuppressWarnings("unused")
		private static java.sql.Date getCurrentDateFromDB() {
			return (java.sql.Date) getCurrentSysDate(false);
		}

		/**
		 * 获取当前数据库时间，只能在<b>DAO</b>或<b>service</b>层调用
		 * 
		 * @return java.sql.Date
		 * @throws SysException
		 * @throws AppException
		 */
		private static Timestamp getCurrentTimestampFromDB() {
			return (java.sql.Timestamp) getCurrentSysDate(true);
		}

		/**
		 * 
		 * @param showTime
		 * @return java.util.Date
		 * @throws DataAccessException
		 */
		private static java.util.Date getCurrentSysDate(boolean showTime)
				throws DataAccessException {
			java.util.Date date = null;
			// String sql = " select sysdate from dual ";

			String sql = "select now()";

			if (showTime) {
				date = SysContextUtil.getConfigedSimpleJdbcTemplate()
						.queryForObject(sql, java.sql.Timestamp.class);
			} else {
				date = SysContextUtil.getConfigedSimpleJdbcTemplate()
						.queryForObject(sql, java.sql.Date.class);
			}

			return date;
		}
	}
}
