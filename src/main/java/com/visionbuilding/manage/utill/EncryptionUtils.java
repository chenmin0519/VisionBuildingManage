package com.visionbuilding.manage.utill;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;

/**
 * 加密工具，先用MD5加密，再用SHA256加密
 */
public class EncryptionUtils {

   protected static final Log log = LogFactory.getLog(EncryptionUtils.class);

    /**
     * ApacheSHA256加密
     * @param str
     * @return
     */
    public static String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return encdeStr;
    }

    /**
     * ApacheMD5加密
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return encdeStr;
    }

    public static  String getCiphertext(String par){
        return getSHA256Str(getMD5Str(par));
    }

//    public static void main(String[] strings) {
//        System.out.println(getCiphertext("123456"));
//    }
}
