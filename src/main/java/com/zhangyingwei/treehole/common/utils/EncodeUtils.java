package com.zhangyingwei.treehole.common.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangyw on 2017/5/5.
 */
public class EncodeUtils {
    /**
     * encode string by md5
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5String(String str) throws NoSuchAlgorithmException {
        if(StringUtils.isNotEmpty(str)){
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] resultBytes = digest.digest(str.getBytes());
            return HexBin.encode(resultBytes);
        }
        return "";
    }
}
