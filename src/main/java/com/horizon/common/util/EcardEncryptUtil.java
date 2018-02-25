package com.horizon.common.util;
/**
 * 签名算法及加解密算法
 * */
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 **/
public class EcardEncryptUtil {
    /***
     * MD5加码 生成32位md5码
     */
    public static String md532(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 加密解密算法
     * @param inStr 加密字符串
     * @param secretKey  秘钥
     * 算法：
     *    1：加密字符串和秘钥转换成字符数组；
     *    2：秘钥去重复
     *    3：循环一（秘钥字符串数组）{ 循环二（加密字符串数组）{
     *            秘钥字符的ASC码 与 加密字符的ASC码 进行二进制异或运算
     *         }
     *       }
     *    4：把字符串转为16进制
     */
    private static String convert(String inStr, String secretKey) {
        char[] a = inStr.toCharArray();
        char[] s = rmRepeated(secretKey).toCharArray();
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[j] = (char) (a[j] ^ s[i]);
            }
        }
        String r = new String(a);
        return r;
    }
    /**
     * 清除字符串中重复字母算法
     * @param s
     * @return
     */
    private static String rmRepeated(String s) {
        int len = s.length();
        int k = 0;
        int count = 0;
        String str = "";
        char[] c = new char[len];
        for (int i = 0; i < len; i++) {
            c[i] = s.charAt(i);
        }
        for (int i = 0; i < len; i++) {
            k = i + 1;
            while (k < len - count) {
                if (c[i] == c[k]) {
                    for (int j = k; j < len - 1; j++) {
                        c[j] = c[j + 1];// 出现重复字母，从k位置开始将数组往前挪位
                    }
                    count++;// 重复字母出现的次数
                    k--;
                }
                k++;
            }
        }
        for (int i = 0; i < len - count; i++) {
            str += String.valueOf(c[i]);
        }
        return str;
    }
    /*
    * 将字符串编码成16进制数字,适用于所有字符（包括中文）
    */
    private static String hexString = "0123456789ABCDEF";
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        String r="";
        try {
            byte[]  bytes = str.getBytes("UTF-8");
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            // 将字节数组中每个字节拆解成2位16进制整数
            for (int i = 0; i < bytes.length; i++) {
                sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
                sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
            }
            r=sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r;
    }
    /*
    * 将16进制数字解码成字符串,适用于所有字符（包括中文）
    */
    public static String decode(String bytes) {
        String r="";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
            // 将每2位16进制整数组装成一个字节
            for (int i = 0; i < bytes.length(); i += 2){
                baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
            }
            r= new String(baos.toByteArray(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r;
    }
    /**
     * 加密
     *
     * @param inStr
     *            原字符串
     * @param secretKey
     *            秘钥
     * @return
     */
    public static String encrypt(String inStr, String secretKey) {
        String hexStr=convert(inStr, secretKey);
        return encode(hexStr);
    }

    /**
     * 解密
     *
     * @param inStr
     *            原字符串
     * @param secretKey
     *            秘钥
     * @return
     */
    public static String decrypt(String inStr, String secretKey) {
        String hexStr=decode(inStr);
        return convert(hexStr, secretKey);
    }

    // 测试主函数
    public static void main(String args[]) {
        String s = new String("12bvdde`中户人民共和国，1234@￥#%&*（）-=|+_}{[]/.,;:,.>》》。，《dkfjaskfaskdjfkdasj");
        String md5 = md532(s);
        String enc = encrypt(s, "f8ee541137a2aa381abaac17886653ba");
        String dnc = decrypt(enc, "f8ee541137a2aa381abaac17886653ba");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + md5);
        System.out.println("加密的：" + enc);
        System.out.println("解密的：" + dnc);
    }
}
