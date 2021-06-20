package com.small.rpc.common.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static boolean isEmpty(String s) {
        if (s != null) {
            s = s.trim();
        }
        return StringUtils.isEmpty(s);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }


    /**
     * 分割固定格式的字符串
     */
    public static String[] split(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }
}
