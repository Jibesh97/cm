package com.horizon.common.util;  
  
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

public class ThreeDESUtil {  
    // 24字节的密钥
	final static byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 };
    private static final String Algorithm = "DESede"; // 定义 加密算法,可用  
                                                        // DES,DESede,Blowfish  
    
    private static Logger log = Logger.getLogger(ThreeDESUtil.class);
  
    /** 
     * 加密方法 
     *  
     * @param keybyte 
     *            加密密钥，长度为24字节 
     * @param src 
     *            被加密的数据缓冲区（源） 
     * @return 
     */  
    public static byte[] encryptMode(byte[] src) {  
        try {  
            // 生成密钥  
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);  
  
            // 加密  
            Cipher c1 = Cipher.getInstance(Algorithm);  
            c1.init(Cipher.ENCRYPT_MODE, deskey);  
            return c1.doFinal(src);  
        } catch (java.security.NoSuchAlgorithmException e1) {  
            log.error(e1.getMessage());
        } catch (javax.crypto.NoSuchPaddingException e2) {  
        	log.error(e2.getMessage());  
        } catch (java.lang.Exception e3) {  
        	log.error(e3.getMessage());  
        }  
        return null;  
    }  
  
    /** 
     * 解密 
     *  
     * @param keybyte 
     *            加密密钥，长度为24字节 
     * @param src 
     *            加密后的缓冲区 
     * @return 
     */  
    public static byte[] decryptMode(byte[] src) {  
        try {  
            // 生成密钥  
            SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);  
  
            // 解密  
            Cipher c1 = Cipher.getInstance(Algorithm);  
            c1.init(Cipher.DECRYPT_MODE, deskey);  
            return c1.doFinal(src);  
        } catch (java.security.NoSuchAlgorithmException e1) {  
            log.error(e1.getMessage());
        } catch (javax.crypto.NoSuchPaddingException e2) {  
        	log.error(e2.getMessage());  
        } catch (java.lang.Exception e3) {  
        	log.error(e3.getMessage());  
        }  
        return null;  
    }  
  
  
  
  
}  