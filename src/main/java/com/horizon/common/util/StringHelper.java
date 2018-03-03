package com.horizon.common.util;


public class StringHelper {
	
	private StringHelper() { /* static methods only - hide constructor */
	}
	
	public static String columnNameToProperty(String columnName){
		if (columnName.indexOf("_") != -1) {
			return removeUnderscores(columnName);
		}
		return columnName(columnName);
	}
	
	/**
	 * @return
	 */
	public static String removeUnderscores(String columnName){
		String[] strs = columnName.split("_");
		return columnName(strs);
	}
	/**
	 * @param strs
	 * @return
	 */
	public static String columnName(String ...strs){
		StringBuilder result = new StringBuilder("");
		for(String string: strs){
			result.append(string.toLowerCase().substring(0, 1).toUpperCase()+ string.toLowerCase().substring(1));
		}
		return result.toString();
	}
	
	public static String trimQuotes(String content) {
		return content.replaceAll("\"","");
	}
	
	public static String checkQuotes(String content) {
		if(content.indexOf("\"") != -1){
			return "\"" + trimQuotes(content) + "\"";
		}
		return "\"" + content + "\"";
	}

	public static void main(String[] args) {
		String ss = "\"" + "190298289" + "\"";
		System.out.println(checkQuotes(ss));

	}
	/**
	 * 
	 * @param srcStr
	 * @return String
	 */
	public static String decodeHtml(String srcStr) {

		srcStr = srcStr.replaceAll("<", "&lt;");
		srcStr = srcStr.replaceAll(">", "&gt;");
//		srcStr = srcStr.replaceAll("&", "&amp;");
//		srcStr = srcStr.replaceAll("\"", "&quot;");
//		srcStr = srcStr.replaceAll("®", "&copy;");
//		srcStr = srcStr.replaceAll("®", "&reg;");
//		srcStr = srcStr.replaceAll("�?", "&trade;");
		//srcStr = srcStr.replaceAll(" ", "&nbsp;");
		srcStr = srcStr.replaceAll(System.getProperty("line.separator"),
				"<br/>");
		return srcStr;
	}

}
