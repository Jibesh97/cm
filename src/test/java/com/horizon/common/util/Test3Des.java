package com.horizon.common.util;

import java.io.IOException;
import java.security.Security;

import org.apache.commons.codec.binary.Base64;

public class Test3Des {
	
	   /** 
     * 测试 
     *  
     * @param args 
     * @author SHANHY 
     * @throws IOException 
     * @date 2015-8-18 
     */  
    public static void main(String[] args) throws IOException {  
        // 添加新安全算法,如果用JCE就要把它添加进去  
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());  
  
        String szSrc = "123!@#qwertyuiop[]poiuytrew$%^&*(){}";  
  
        System.out.println("加密前的字符串:" + szSrc);  
  
        byte[] encoded = ThreeDESUtil.encryptMode(szSrc.getBytes());  
        String temp = new String(Base64.encodeBase64(encoded));
        System.out.println("加密后的字符串:" + new String(encoded)+"=="+ temp);  

        byte[] srcBytes = ThreeDESUtil.decryptMode(Base64.decodeBase64(temp.getBytes()));  
        System.out.println("解密后的字符串:" + (new String(srcBytes)));  
    } 

}
