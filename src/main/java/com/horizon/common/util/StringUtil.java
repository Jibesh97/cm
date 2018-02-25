package com.horizon.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * brief description.
 * <p>
 * Date :
 * </p>
 * <p>
 * Module : 常用的字符串操作
 * </p>
 * <p>
 * Description: 此类中封装一些常用的字符串操作 有方法都是静态方法，不需要生成此类的实例
 * 为避免生成此类的实例，构造方法被申明为private类型
 * </p>
 * <p>
 * Remark : 备注
 * </p>
 * 
 * @author
 * @version 1.0
 *          <p>
 *          ------------------------------------------------------------
 *          </p>
 *          <p>
 *          修改历史
 *          </p>
 *          <p>
 *          序号 日期 修改 修改原因
 *          </p>
 *          <p>
 *          1
 *          </p>
 */
public class StringUtil {
	/**
	 * 私有构方法，防止类的实例化，因为工具类不要实例化.
	 * 
	 */
	private StringUtil() {
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数�?.
	 * 
	 * 
	 * @param source
	 *            要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯元素的数组， 如果delim为null则使用号作为分隔字符串
	 * 
	 * @since 0.1
	 */
	public static String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数�?.
	 * 
	 * 
	 * @param source
	 *            要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字
	 * 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯�?元素的数组�??
	 * 
	 * @since 0.2
	 */
	public static String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * 此方法将给出的字符串source使用逗号划分为单词数�?.
	 * 
	 * 
	 * @param source
	 *            �?要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯�?元素的数组�??
	 * 
	 * @since 0.1
	 */
	public static String[] split(String source) {
		return split(source, ",");
	}

	/**
	 * 以指定的字符和长度生成一个该字符的指定长度的字符�?.
	 * 
	 * 
	 * @param c
	 *            指定的字�?
	 * 
	 * @param length
	 *            指定的长�?
	 * 
	 * @return �?终生成的字符�?
	 * 
	 * @since 0.6
	 */
	public static String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += c;
		}
		return ret;
	}

	/**
	 * 去除左边多余的空�?.
	 * 
	 * 
	 * @param value
	 *            待去左边空格的字符串
	 * @return 去掉左边空格后的字符�?
	 * 
	 * @since 0.6
	 */
	public static String trimLeft(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char[] ch = result.toCharArray();
		int index = -1;
		for (int i = 0; i < ch.length; i++) {
			if (Character.isWhitespace(ch[i])) {
				index = i;
			} else {
				break;
			}
		}
		if (index != -1) {
			result = result.substring(index + 1);
		}
		return result;
	}

	/**
	 * 去除右边多余的空�?.
	 * 
	 * 
	 * @param value
	 *            待去右边空格的字符串
	 * @return 去掉右边空格后的字符�?
	 * 
	 * @since 0.6
	 */
	public static String trimRight(String value) {
		String result = value;
		if (result == null) {
			return result;
		}
		char[] ch = result.toCharArray();
		int endIndex = -1;
		for (int i = ch.length - 1; i > -1; i--) {
			if (Character.isWhitespace(ch[i])) {
				endIndex = i;
			} else {
				break;
			}
		}
		if (endIndex != -1) {
			result = result.substring(0, endIndex);
		}
		return result;
	}

	/**
	 * 根据转义列表对字符串进行转义.
	 * 
	 * 
	 * @param source
	 *            待转义的字符�?
	 * 
	 * @param escapeCharMap
	 *            转义列表
	 * @return 转义后的字符�?
	 * 
	 * @since 0.6
	 */
	public static String escapeCharacter(String source,
			HashMap<?, ?> escapeCharMap) {
		if (isEmpty(source)) {
			return source;
		}
		if (escapeCharMap.size() == 0) {
			return source;
		}
		StringBuffer sb = new StringBuffer();
		StringCharacterIterator sci = new StringCharacterIterator(source);
		for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci
				.next()) {
			String character = String.valueOf(c);
			if (escapeCharMap.containsKey(character)) {
				character = (String) escapeCharMap.get(character);
			}
			sb.append(character);
		}
		return sb.toString();
	}

	/**
	 * 得到字符串的字节长度.
	 * 
	 * 
	 * @param source
	 *            字符�?
	 * 
	 * @return 字符串的字节长度
	 * @since 0.6
	 */
	public static int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 得到字符串中的子串的个数.
	 * 
	 * 
	 * @param source
	 *            字符�?
	 * 
	 * @param sub
	 *            子串
	 * @return 字符串中的子串的个数
	 * @since 0.6
	 */
	public static int getSubtringCount(String source, String sub) {
		if (isEmpty(source)) {
			return 0;
		}
		int count = 0;
		int index = source.indexOf(sub);
		while (index >= 0) {
			count++;
			index = source.indexOf(sub, index + 1);
		}
		return count;
	}

	/**
	 * 字符串判断为�?.
	 * 
	 * 
	 * @param string
	 *            字符�?
	 * @param nullretrun
	 *            子串
	 * @return 为null返回空串
	 * @since 0.6
	 */
	public static String getString(String string, String nullretrun) {

		return StringUtils.isEmpty(string) ? nullretrun : string;

	}

	/**
	 * 字符串判断为�?.
	 * 
	 * 
	 * @param string
	 *            字符�?
	 * 
	 * @param nullretrun
	 *            子串
	 * @return 为null返回空串,不为null返回字符串trim�?
	 * @since 0.6
	 */
	public static String getTrimString(String string, String nullretrun) {
		return string == null ? nullretrun : string.trim();
	}

	/**
	 * 字符串转码，将ISO8859-1转化为UTF-8.
	 * 
	 * 
	 * @param str
	 *            字符�?
	 * 
	 * @return 返回转码后字符串
	 * @throws UnsupportedEncodingException
	 * @since 0.6
	 */
	public static String transEncode(String str) {

		String transStr = "";
		try {
			transStr = new String(getString(str, "").getBytes("ISO8859-1"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			transStr = str;
		}
		return transStr;

	}

	/**
	 * 字符串不区分大小写，统一转换成大�?.
	 * 
	 * 
	 * 
	 * @param str
	 *            字符�?
	 * 
	 * 
	 * @return 返回大写后字符串
	 * @throws UnsupportedEncodingException
	 * @since 0.6
	 */
	public static String stringChangeUpper(String str) {
		if (!isEmpty(str)) {
			str = str.toUpperCase();
		} else {
			str = "";
		}
		return str;

	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 关闭PrintWriter实例
	 * </p>
	 * <p>
	 * 备注 :
	 * </p>
	 * 
	 * @param out
	 *            PrintWriter对象
	 */
	public static void closePrintWriter(PrintWriter out) {
		if (out != null) {
			out.flush();
			out.close();
		}
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 关闭OutputStream实例
	 * </p>
	 * <p>
	 * 备注 : 用于关闭OutputStream及其子类
	 * </p>
	 * 
	 * @param out
	 *            PrintWriter对象
	 * @throws IOException
	 *             IO异常
	 */
	public static void closeOutputStream(OutputStream out) throws IOException {
		if (out != null) {
			out.flush();
			out.close();
		}
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 关闭InputStream实例
	 * </p>
	 * <p>
	 * 备注 : 用于关闭InputStream及其子类
	 * </p>
	 * 
	 * @param in
	 *            输入�?
	 * @throws IOException
	 *             IO异常
	 */
	public static void closeInputStream(InputStream in) throws IOException {
		if (in != null) {
			in.close();
		}
	}

	/**
	 * 判断是否是一个中文汉�?.
	 * 
	 * @param c
	 *            字符
	 * @param charset
	 *            编码方式
	 * @return true表示是中文汉字，false表示是英文字�?
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c, String charset)
			throws UnsupportedEncodingException {
		// 如果字节数大�?1，是汉字
		return getByteLength(String.valueOf(c)) > 1;
	}

	/**
	 * 按字节截取字符串.
	 * 
	 * @param orignal
	 *            原始字符�?
	 * @param count
	 *            截取位数
	 * @param charset
	 *            编码方式
	 * @return 截取后的字符�?
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String getChineseSubString(String orignal, int count,
			String charset) throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (!isEmpty(orignal)) {
			// 将原始字符串转换为GBK编码格式
			// orignal = new String(orignal.getBytes(), charset);
			// 要截取的字节数大�?0，且小于原始字符串的字节�?
			if (count > 0 && count < getByteLength(orignal)) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					// charAt(int index)也是按照字符来分解字符串�?
					c = orignal.charAt(i);
					if (isChineseChar(c, charset)) {
						if (i == count - 1) {
							// 如果�?后一个字节时遇到中文，不能截取半个中文，�?以舍�?
							break;
						}
						// 遇到中文汉字，截取字节�?�数�?1
						--count;
					}
					buff.append(c);
				}

				return buff.toString();
			}
		}
		return orignal;
	}

	/**
	 * 判断String对象是否为null.
	 * 
	 * @param obj
	 *            obj
	 * @return boolean
	 */
	public static boolean isEmpty(String obj) {
		boolean result = true;
		if (obj == null || obj.length() == 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 判断字符串长度是否符合数据库长度
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @param length
	 *            长度
	 * @return boolean
	 */
	public static boolean checkLength(String str, int length) {
		if (!StringUtil.isEmpty(str)) {
			if (getByteLength(str) > length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证字符串str的长度是否等于length，如果是返回false，否则返回true
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @param length
	 *            长度
	 * @return boolean
	 */
	public static boolean checkEqualLength(String str, int length) {
		if (!StringUtil.isEmpty(str)) {
			if (getByteLength(str) != length) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 由数字和26个英文字母组成的字符�?
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkNumEng(String str) {
		boolean bol = str.matches("^[A-Za-z0-9]+$");
		return bol;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : �?26个英文字母组成的字符�?
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkEng(String str) {
		boolean bol = str.matches("^[A-Za-z]+$");
		return bol;

	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : �?26个英文字母组成的字符�?+空格
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkEngSpace(String str) {
		boolean bol = str.matches("^[ A-Za-z]+$");
		return bol;

	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证数字
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkNumber(String str) {
		boolean bol = str.matches("^[0-9]+$");
		return bol;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证电话号码
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkPhone(String str) {
		String phone = "([0-9\\-])+";
		Pattern pattern = Pattern.compile(phone);
		Matcher match = pattern.matcher(str);
		return match.matches();
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证邮编
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkPostCode(String str) {
		boolean bol = str.matches("^[1-9]\\d{5}$");
		return bol;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证手机
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param str
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkMobile(String str) {
		boolean bol = str.matches("^1(3|5|8)\\d{9}$");
		return bol;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 验证邮箱
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param mail
	 *            字符�?
	 * @return boolean
	 */
	public static boolean checkEmail(String mail) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return m.find();
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 输入日期格式校验YYYY-MM-DD
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param date
	 *            日期
	 * @return boolean
	 */
	public static boolean checkDate(String date) {
		boolean flag = false;
		SimpleDateFormat sDFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date tempDate = sDFormat.parse(date);
			String tempFormatDate = sDFormat.format(tempDate);
			if (date.equals(tempFormatDate)) {
				flag = true;
			}
		} catch (ParseException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 校验金额
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param money
	 *            金额
	 * @return boolean
	 */
	public static boolean checkMoney(String money) {
		boolean flag = false;
		try {
			new BigDecimal(money);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 校验金额之和
	 * </p>
	 * <p>
	 * 备注 : admacm上传文件专用
	 * </p>
	 * 
	 * @param money1
	 *            金额1
	 * @param money2
	 *            金额2
	 * @param money3
	 *            金额3
	 * @param money4
	 *            金额4
	 * @return boolean
	 */
	public static boolean checkTotalMoney(String money1, String money2,
			String money3, String money4) {
		boolean flag = false;
		try {
			if (StringUtil.isEmpty(money1)) {
				money1 = "0";
			}
			if (StringUtil.isEmpty(money2)) {
				money2 = "0";
			}
			if (StringUtil.isEmpty(money3)) {
				money3 = "0";
			}
			if (StringUtil.isEmpty(money4)) {
				money4 = "0";
			}
			BigDecimal formatMoney1 = new BigDecimal(money1);
			BigDecimal formatMoney2 = new BigDecimal(money2);
			BigDecimal formatMoney3 = new BigDecimal(money3);
			BigDecimal formatMoney4 = new BigDecimal(money4);
			if (new BigDecimal("0").equals(formatMoney1.add(formatMoney2).add(
					formatMoney3))) {
				if (!"0".equals(money4)) { // 不允�?4项均�?0,确保总金额不�?0
					flag = true;
				}
			} else {
				if (formatMoney4.equals(formatMoney1.add(formatMoney2).add(
						formatMoney3))) {
					if (!"0".equals(money4)) { // 不允�?4项均�?0,确保总金额不�?0
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 校验机票�? 1234567890123-12345
	 * </p>
	 * <p>
	 * 备注 : admacm上传文件专用
	 * </p>
	 * 
	 * @param ticketNo
	 *            机票�?
	 * @return boolean
	 */
	public static boolean checkTicketNo(String ticketNo) {
		boolean flag = false;
		if (!StringUtil.isEmpty(ticketNo)) {
			if (ticketNo.length() == 10 && checkNumber(ticketNo)) {
				flag = true;
			}
			if (ticketNo.length() <= 16 && ticketNo.length() > 10
					&& ticketNo.indexOf("-") == 10
					&& checkNumber(ticketNo.substring(0, 10))
					&& checkNumber(ticketNo.substring(11, ticketNo.length()))) {
				int ticketNoBak = ticketNo.substring(11, ticketNo.length())
						.length();
				if (Integer.parseInt(ticketNo.substring(11, ticketNo.length())) > Integer
						.parseInt(ticketNo.substring(9 - ticketNoBak + 1, 10))) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 获取数组的长度，当数组为空时，返回长度为0
	 * </p>
	 * <p>
	 * 备注 :
	 * </p>
	 * 
	 * @param array
	 *            传入的数�?
	 * @return int
	 */
	public static int getLength(Object[] array) {
		int length = 0;
		if (array != null) {
			length = array.length;
		}
		return length;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 获取list的大小，当list为空时，返回大小�?0
	 * </p>
	 * <p>
	 * 备注 :
	 * </p>
	 * 
	 * @param list
	 *            传入的数�?
	 * @return int
	 */
	public static int getLength(List<?> list) {
		int size = 0;
		if (list != null) {
			size = list.size();
		}
		return size;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 将金钱转化成汉字大写
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param amount
	 *            ####.00格式的钱
	 * @return String
	 */
	public static String money2RMB(String amount) {
		String returnStr = "";
		String[] strFigure = { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?",
				"�?", "�?" };
		String[] strUnit = { "�?", "�?", "�?", "�?", "�?", "�?", "�?", "�?",
				"�?", "�?", "�?", "�?", "�?", "�?", "�?" };

		String strAmount = amount;
		strAmount = amount.replaceAll("\\.", "");
		strAmount = strAmount.replaceAll(",", "");

		int strLength = strAmount.length();
		String myNum = "";
		for (int i = 1; i <= strLength; i++) {
			myNum = strAmount.substring(i - 1, i);
			int len = strLength + 1 - i;
			returnStr += strFigure[Integer.parseInt(myNum)] + strUnit[len];
		}

		for (int i = 1; i < strUnit.length; i++) {
			String tempStr = "";
			if (!(strUnit[i].equals("�?") || strUnit[i].equals("�?"))) {
				tempStr = "�?" + strUnit[i] + "�?";
				returnStr = returnStr.replaceAll(tempStr, "�?");
			}
		}

		returnStr = returnStr.replaceAll("零亿", "�?");
		returnStr = returnStr.replaceAll("零万", "�?");
		returnStr = returnStr.replaceAll("零仟", "�?");
		returnStr = returnStr.replaceAll("零佰", "�?");
		returnStr = returnStr.replaceAll("零拾", "�?");
		returnStr = returnStr.replaceAll("零元", "�?");
		returnStr = returnStr.replaceAll("零角", "�?");
		returnStr = returnStr.replaceAll("亿万", "亿零");

		if (returnStr.endsWith("零分") && !returnStr.endsWith("角零�?")) {
			returnStr = returnStr.replaceAll("零分", "元整");
		} else {
			returnStr = returnStr.replaceAll("零分", "");
		}
		returnStr = returnStr.replaceAll("元元", "�?");
		return returnStr;
	}

	/**
	 * 方法�?要描述信�?.
	 * <p>
	 * 描述 : 将字符串中的html转义字符进行转义
	 * </p>
	 * <p>
	 * 备注 :
	 * </p>
	 * 
	 * @param srcStr
	 *            含有html要转义的字符的字符串
	 * @return String
	 */
	public static String decodeHtml(String srcStr) {
		/**
		 * html要转义的字符 HTML 源代�? 显示结果 描述 &lt; < 小于号或显示标记 &gt; > 大于号或显示标记 &amp; &
		 * 可用于显示其它特殊字�? &quot; " 引号 &reg; ® 已注�? &copy; © 版权 &trade; �? 商标
		 * &ensp; 半个空白�? &emsp; �?个空白位 &nbsp; 不断行的空白
		 */
		srcStr = srcStr.replaceAll("<", "&lt;");
		srcStr = srcStr.replaceAll(">", "&gt;");
		srcStr = srcStr.replaceAll("&", "&amp;");
		srcStr = srcStr.replaceAll("\"", "&quot;");
		srcStr = srcStr.replaceAll("®", "&copy;");
		srcStr = srcStr.replaceAll("®", "&reg;");
		srcStr = srcStr.replaceAll("�?", "&trade;");
		srcStr = srcStr.replaceAll(" ", "&nbsp;");
		srcStr = srcStr.replaceAll(System.getProperty("line.separator"),
				"<br/>");
		return srcStr;
	}

	/**
	 * 获取请求的ip地址.
	 * <p>
	 * 描述 : 方法的主要功能和使用场合
	 * </p>
	 * <p>
	 * 备注 : 其他对方法的说明信息
	 * </p>
	 * 
	 * @param request
	 *            请求
	 * @return string
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 字符串比较，equals方法
	 * <p>
	 * 描述 : 防止调用equals的属性为空后抛出空指针异常，为此做特殊处理
	 * </p>
	 * 
	 * @param String
	 *            第一个字符串
	 * @param String
	 *            第二个字符串
	 * @return boolean
	 * @author esoft_liy
	 */
	public static Boolean equals(String strA, String strB) {
		if (strA != null) {
			// strA不为空的时候判断
			return strA.equals(strB);
		} else {
			// strB不为空的时候判断
			if (strB != null) {
				return strB.equals(strA);
			} else {
				// 两个对象都为空的话，视为相等
				return true;
			}
		}
	}

	/**
	 * 对字符串左右加“%”，进行模糊查询
	 * 
	 * @param str
	 * @param leftFlag
	 *            字符串左面加“%”
	 * @param rightFlag
	 *            字符串右面加“%”
	 * @return
	 */
	public static String encodeLikeSign(String str, boolean leftFlag,
			boolean rightFlag) {
		if (str == null || "".equals(str)) {
			return null;
		}
		str = trimLeft(str);
		str = trimRight(str);
		if (leftFlag) {
			str = "%" + str;
		}
		if (rightFlag) {
			str = str + "%";
		}
		return str;
	}

	/**
	 * 对带“%”的字符串解码，去掉“%”
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeLikeSign(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		return str.replace("%", "");
	}

	public static String replace(String source, String from, String to) {
		if (source == null) {
			return null;
		}
		int i = 0;
		if ((i = source.indexOf(from, i)) >= 0) {
			char[] cSrc = source.toCharArray();
			char[] cTo = to.toCharArray();
			int len = from.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = source.indexOf(from, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return source;
	}

	public static String toString(Object object) {
		if (null == object) {
			return "";
		}
		return String.valueOf(object);
	}
	//生成UUID
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	} 
  
	/**
	 * 替换空格符号
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
