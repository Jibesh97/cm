package com.horizon.common.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWraper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        //Constants.MY_LOG.debug("getParameter----->转义处理");
        return clearXss(super.getParameter(name));
        //return xssEncode(super.getParameter(name));
    }
    
    @Override
    public String getHeader(String name) {
        //Constants.MY_LOG.debug("getHeader----->转义处理");
        return clearXss(super.getHeader(name)); 
        //return xssEncode(super.getParameter(name));
    }
    
    @Override
    public String[] getParameterValues(String name) {
        //Constants.MY_LOG.debug("getParameterValues----->转义处理");
        if(!StringUtils.isEmpty(name)){
            String[] values = super.getParameterValues(name);
            if(values != null && values.length > 0){
                String[] newValues = new String[values.length];
                
                for(int i =0; i< values.length; i++){
                    //newValues[i] = clearXss(values[i]);// 保留勿删
                    newValues[i] = clearXss(values[i]);
                }
                return newValues;
            }
        }
        return null;
    }

    /**
     *  
     * 处理字符转义【勿删，请保留该注释代码】
     * @param value
     * @return
     * 
     */
    private String clearXss(String value){
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replace("script", "");
        return value;
    }
    
//    /** 
//     * 将特殊字符替换为全角 
//     * @param s 
//     * @return 
//     */  
//    private  String xssEncode(String s) {  
//        if (s == null || s.isEmpty()) {  
//            return s;  
//        }  
//        StringBuilder sb = new StringBuilder();  
//        for (int i = 0; i < s.length(); i++) {  
//            char c = s.charAt(i);  
//            switch (c) {  
//            case '>':  
//                sb.append('＞');// 全角大于号  
//                break;  
//            case '<':  
//                sb.append('＜');// 全角小于号  
//                break;  
//            case '\'':  
//                sb.append('‘');// 全角单引号  
//                break;  
//            case '\"':  
//                sb.append('“');// 全角双引号  
//                break;  
//            case '&':  
//                sb.append('＆');// 全角＆  
//                break;  
//            case '\\':  
//                sb.append('＼');// 全角斜线  
//                break;  
//            case '/':  
//                sb.append('／');// 全角斜线  
//                break;  
//            case '#':  
//                sb.append('＃');// 全角井号  
//                break;  
//            case '(':  
//                sb.append('（');// 全角(号  
//                break;  
//            case ')':  
//                sb.append('）');// 全角)号  
//                break;  
//            default:  
//                sb.append(c);  
//                break;  
//            }  
//        }  
//        return sb.toString();  
//    }  
}