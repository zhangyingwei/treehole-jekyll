package com.zhangyingwei.treehole.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangyw on 2017/6/15.
 * 时间处理工具类
 */
public class DateUtils {

    /**
     * 获取时间戳
     * @return
     */
    public static Long getTimeStamp(){
        return new Date().getTime();
    }

    /**
     * 获取几天前 凌晨的 时间戳
     * @param amount
     * @return
     */
    public static Long getTimeStampBefore(int amount) {
        return getDateBefore(amount).getTime();
    }

    public static Date getDateBefore(int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE,-amount);
        return calendar.getTime();
    }

    /**
     * 把时间戳按照指定的格式返回字符串时间
     * @param timestamp
     * @param patten
     * @return
     */
    public static String getDateBy(Long timestamp,String patten) {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        return format.format(new Date(timestamp));
    }

    /**
     * 按照指定格式获取前 amount 天的日期
     * @param amount
     * @param patten
     * @return
     */
    public static String getDateBeforeBy(int amount, String patten) {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        return format.format(getDateBefore(amount));
    }
}
