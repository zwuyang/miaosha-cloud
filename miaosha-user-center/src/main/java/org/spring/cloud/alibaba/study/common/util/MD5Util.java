package org.spring.cloud.alibaba.study.common.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yzw
 * @Classname MD5Util
 * @Description TODO
 * @Date 2020/8/13 10:44
 */
public class MD5Util {

    public static String md5(String key){
        return DigestUtils.md5Hex(key);
    }

    public static final String SALT = "zx1gt5j89sfd";

    public static String encode(String pass){
        String str = "" + SALT.charAt(2)+ SALT.charAt(1) + pass + SALT.charAt(6) + SALT.charAt(8);
        return md5(str);
    }

    public static String encode(String original, String salt){
        String str = "" + salt.charAt(2)+ salt.charAt(1) + original + salt.charAt(0) + SALT.charAt(3);
        return encode(md5(str));
    }


    public static String encodeDBPass(String pass, String salt){
        return encode(pass, salt);
    }


}
