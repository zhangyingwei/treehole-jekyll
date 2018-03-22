package com.zhangyingwei.treehole.common.utils;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        return System.currentTimeMillis();
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

    /**
     * 获取当前时间
     * @return
     */
    public static String now(){
        return formate("yyyy-MM-dd HH:mm:ss", new Date());
    }

    /**
     * 格式化时间
     * @param patten
     * @param date
     * @return 格式化之后的时间
     */
    public static String formate(String patten,Date date){
        return new SimpleDateFormat(patten,Locale.US).format(date);
    }

    /**
     * 格式化日期字符串为 date
     * @return
     */
    public static Date formate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }

    /**
     * 格式化日期字符串为 date
     * @return
     */
    public static Date formateData(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    /**
     * 日期转化为 XML 模式 将日期转化为 XML 模式 (ISO 8601) 的格式。
     * @return
     */
    public static String toXmlSchema(Date date){
        return ISO8601DateFormat.getDateTimeInstance().format(date);
    }

    /**
     * 日期转化为 XML 模式 将 XML 模式 (ISO 8601) 的格式转化为日期格式。
     * @param dateString
     * @return
     */
    public static Date fromXmlSchema(String dateString) throws ParseException {
        return ISO8601DateFormat.getDateTimeInstance().parse(dateString);
    }

    /**
     * 日期转化为 RFC-822 格式 将日期转化为 RFC-822 格式，用于 RSS 订阅。
     * @param date
     * @return
     */
    public static String toRFC822(Date date){
        SimpleDateFormat format=new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
        return format.format(date);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtils.formate("MMM d", new Date()));
        System.out.println(DateUtils.formate("MMMMM d", new Date()));
    }
}
