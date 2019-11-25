package com.frozen.utils;

import java.util.List;
import java.util.Objects;

/**
 * <program> gitdatastructure </program>
 * <description> 工具类 </description>
 *
 * @author : lw
 * @date : 2019-11-25 19:27
 **/
public class MyUtils {
    public static byte binary2Byte(String binaryString) {
        if (Objects.isNull(binaryString)) {
            throw new IllegalArgumentException("参数为空");
        }
        int length = binaryString.length();
        char ch;
        //校验参数
        for (int i = 0; i < length; i++) {
            ch = binaryString.charAt(i);
            if (ch != '0' && ch != '1') {
                throw new IllegalArgumentException("参数不是二进制字符串");
            }
        }
        return (byte)Integer.parseInt(binaryString, 2);
    }

    public static byte[] byteList2Array(List<Byte> byteList) {
        if (Objects.isNull(byteList)) {
            throw new IllegalArgumentException("参数为空");
        }
        int length = byteList.size();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }
}
