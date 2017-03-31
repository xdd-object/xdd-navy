package com.java.xdd.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huanghu on 2017/1/27.
 */
public class DateUtil {
    private DateUtil(){}

    /**
     * 时间格式化
     * @param date
     * @param format 格式化，默认yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDate(Date date,String format){
        format = StringUtils.isEmpty(format) ? "yyyy-MM-dd HH:mm:ss" : format;
        return new SimpleDateFormat(format).format(date);
    }
}
