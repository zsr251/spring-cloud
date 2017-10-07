package com.jc.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


/**
 * MD5工具类
 * @author wang.sen
 *
 */
public class MD5Helper {
	public static Logger logger= LoggerFactory.getLogger(MD5Helper.class);

    /**
     * 
     * 获取MD5值
     * 
     * @param inputText
     * @return
     */
    public static String getMD5(String inputText) {
        if (inputText == null) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance("md5");
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            return hex(s);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } 
        return encryptText;
    }

    /**
     * 
     * 返回十六进制字符串
     * 
     * @param arr
     * @return
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

}
