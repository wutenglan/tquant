package com.goldskyer.tquant.common.util;

/**
 * 
 */

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Lin Yaohua
 * @version 2012-11-12
 */
public class StringUtil
{

	public static boolean isEmail(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}

	/**
	 * 校验是否是电话号码
	 * @param s
	 * @return
	 */
	public static boolean isTelNumber(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("^[0-9]{6,20}$");
	}

	/**
	 * 校验是否是手机号码 以下3种符合 13810892478 013810892478 8613810892478
	 * @param s
	 * @return
	 */
	public static boolean isMobilePhone(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("^(86|0)?1\\d{10}$");
	}

	/**
	 * 是否合法的验证码
	 * @param s
	 * @return
	 */
	public static boolean isLegalCertCode(String s, int minLength, int maxLength)
	{
		if (minLength < 0 || minLength > maxLength || StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("[a-zA-Z\\d]{" + minLength + "," + maxLength + "}");
	}

	/**
	 * 是否合法的验证码
	 * @param s
	 * @return
	 */
	public static boolean isLegalBankAccount(String s, int minLength, int maxLength)
	{
		if (minLength < 0 || minLength > maxLength || StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("\\d{" + minLength + "," + maxLength + "}");
	}

	/**
	 * 验证身份证号码格式
	 * @param s
	 * @return
	 */
	public static boolean isIDno(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return false;
		}
		return s.matches("^((\\d{15})|(\\d{17}([0-9]|[Xx])))$");
	}

	public static String substring(String s, int start, int end)
	{
		if (null == s || start >= end || start < 0 || start >= s.length())
		{
			return "";
		}
		if (s.length() > end)
		{
			return s.substring(start, end);
		}
		else
		{
			return s.substring(start);
		}
	}

	/**
	 * 比较两个对象，相同返回true，不同返回false，<strong>两个都为null认为是相同的</strong>
	 * @author Lin Yaohua
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compare(Object a, Object b)
	{
		if (a == b)
		{
			//a和b为同一字符串或者同为null
			return true;
		}
		if (a != null && a.equals(b))
		{
			//a equals b
			return true;
		}
		return false;
	}

	/**
	 * 比较两个字符串，相同返回true，不同返回false，<strong>两个都是null、“”、“  ”认为是相同的</strong>
	 * <li>null == null</li>
	 * <li>null == ""</li>
	 * <li>null == "  "</li>
	 * <li>" " == "&nbsp;&nbsp;&nbsp;"</li>
	 * 
	 * @author Lin Yaohua
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareIgnoreBlank(String a, String b)
	{
		return compare(StringUtils.trimToEmpty(a), StringUtils.trimToEmpty(b));
	}

	/**
	 * 二级分隔字符串，结果以map格式返回
	 * @param source 被分隔的字符串 e.g. foo=0&bar=1
	 * @param splitRegex 一级分隔符，正则表达式 e.g. \\&
	 * @param indexDelimiter 二级分隔符 e.g. =
	 * @return  字符串解析出来的map e.g. foo:0 bar:1
	 */
	public static Map<String, String> splitStringToMap(String source, String splitRegex, String indexDelimiter)
	{
		Map<String, String> result = new HashMap<String, String>();
		if (StringUtils.isBlank(source) || StringUtils.isBlank(splitRegex) || StringUtils.isBlank(indexDelimiter))
		{
			return result;
		}

		for (String param : source.split(splitRegex))
		{
			if (StringUtils.isBlank(param))
			{
				continue;
			}
			int index = param.indexOf(indexDelimiter);
			if (index > 0)
			{
				result.put(param.substring(0, index), param.substring(index + indexDelimiter.length()));
			}
			else
			{
				result.put(param, "");
			}
		}
		return result;
	}

	public static int getSubStringIndex(String source, String subString, String splitRegex)
	{
		int index = -1;
		if (StringUtils.isBlank(subString) || StringUtils.isBlank(source))
		{
			return -1;
		}
		String[] srcArray = source.split(splitRegex);
		if (srcArray.length > 0)
		{
			for (int i = 0; i < srcArray.length; i++)
			{
				if (subString.equals(srcArray[i]))
				{
					index = i;
					break;
				}
			}
		}

		return index;
	}

	/**
	 * 将16进制字符串转换为字符数组
	 */
	public static byte[] hexStrToBytes(String s)
	{
		byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

	/**
	 * 将字符数组转换为16进制字符串
	 */
	public static String bytesToHexStr(byte[] bcd)
	{
		StringBuilder s = new StringBuilder(bcd.length * 2);

		for (int i = 0; i < bcd.length; i++)
		{
			s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
			s.append(bcdLookup[bcd[i] & 0x0f]);
		}

		return s.toString();
	}

	private static char[] bcdLookup =
	{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 为敏感字符串加上型号
	 *
	 * @param plainStr
	 * @param headLength
	 *            头几位显示
	 * @param tailLength
	 *            末几位显示
	 * @param starLength
	 *            星号的数量
	 * @return
	 */
	public static String getSecretNumberWithStart(String plainStr, int headLength, int tailLength, int starLength)
	{
		if (StringUtils.isBlank(plainStr))
		{
			return "";
		}
		char[] star = new char[starLength];
		Arrays.fill(star, '*');
		String starString = new String(star);

		if (plainStr.length() <= tailLength)
		{
			return plainStr;
		}
		else
		{
			return plainStr.substring(0, headLength) + starString
					+ plainStr.substring(plainStr.length() - tailLength, plainStr.length());
		}
	}

	/**
	 * 返回隐藏的手机号，例如：13812345678转为*********78
	 * @author Lin Yaohua
	 * @param mobile
	 * @return
	 */
	public static String maskMobile(String mobile)
	{
		return getSecretNumberWithStart(mobile, 0, 2, 9);
	}

	/**
	 * 获取卡号后2位，用于前台显示
	 * @param cardNo
	 * @return
	 */
	public static String getCardNoTail(String cardNo)
	{
		return StringUtils.right(cardNo, 2);
	}



	public static boolean isBlank(String[] aString)
	{
		boolean result = true;
		if (aString != null && aString.length != 0)
		{
			for (String string : aString)
			{
				if (StringUtils.isNotBlank(string))
				{
					result = false;
					break;
				}
			}
		}
		return result;
	}

	public static boolean hasChineseCharacter(String content)
	{
		boolean result = false;
		String regEx = "[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		if (m.find())
		{
			result = true;
		}
		return result;
	}

	/**免责声明：
	 * 本函数只计算中文字符，其他乱七八糟的字符一律不计数，未看注释使用造成后果均与本代码无关
	 * @param str
	 * @return
	 */
	public static int chineseCharacterNum(String str)
	{
		if (str == null)
		{
			return 0;
		}
		int length = str.length();
		int count = 0;
		for (int i = 0; i < length; i++)
		{
			if (isChineseCharacter(str.charAt(i)))
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * 本函数只判断中文字符，其他乱七八糟的字符一律返回false
	 * @param ch
	 * @return
	 */
	public static boolean isChineseCharacter(char ch)
	{
		//获取此字符的UniCodeBlock
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		/*Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ： 4E00-9FBF：CJK 统一表意符号 
		 *Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ：F900-FAFF：CJK 兼容象形文字 
		 *Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ：3400-4DBF：CJK 统一表意符号扩展 A
		 *CJK的意思是“Chinese，Japanese，Korea”的简写 ，实际上就是指中日韩三国的象形文字的Unicode编码 
		 *Character.UnicodeBlock.GENERAL_PUNCTUATION ：2000-206F：常用标点 Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ：3000-303F：CJK 符号和标点 Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ：FF00-FFEF：半角及全角形式
		 *CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号 
		 *HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
		 *GENERAL_PUNCTUATION 判断中文的“号  
		 * */
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
		{
			return true;
		}
		return false;
	}

	/**免责声明：
	 * 本函数为带有中文字符的字符串截断，能返回完整的字符，包含中英文外的字符使用造成后果均与本代码无关
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subStringWithChinese(String str, int length)
	{
		if (str == null || str.equals("") || length < 0)
		{
			return str;
		}
		if (length == 0)
		{
			return "";
		}
		int strLen = str.length() + chineseCharacterNum(str) * 2;
		if (strLen <= length)
		{
			return str;
		}
		int count = 0, i = 0;
		for (; i < str.length(); i++)
		{
			if (isChineseCharacter(str.charAt(i)))
			{
				count += 3;
			}
			else
			{
				count++;
			}
			if (count > length)
			{
				break;
			}
		}
		return str.substring(0, i);
	}

	/**输入字符UTF8编码的第一个字节，返回该字符utf8编码的字节数
	 * @param b
	 * @return
	 */
	public static int getUTF8CodeLength(byte b)
	{
		int len = 0;
		byte _3bytes = (byte) 0xF0, _2bytes = (byte) 0xE0;
		//_1bytes = (byte) 0xC0;
		if (b > 0x00)
		{
			len = 1;
		}
		else if (b < 0x00 && b >= _3bytes)
		{
			len = 4;
		}
		else if (b < 0x00 && b >= _2bytes && b < _3bytes)
		{
			len = 3;
		}
		else
		{
			len = 2;
		}
		return len;
	}

	/**针对UTF8编码的字符串截断，能够保持length长度向下取值的字符完整
	 * @param str
	 * @param length
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String subStringWithUTF8Encoded(String str, int length) throws UnsupportedEncodingException
	{
		byte[] aByte = str.getBytes("utf-8");
		int charLen = 0, i = 0;
		for (; i < aByte.length;)
		{
			charLen = getUTF8CodeLength(aByte[i]);
			if (charLen + i > length)
			{
				break;
			}
			i += charLen;
		}
		return new String(aByte, 0, i);
	}
}
