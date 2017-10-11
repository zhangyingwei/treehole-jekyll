package com.zhangyingwei.treehole.common.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by zhangyw on 2017/5/9.
 */
public class TreeHoleUtilsTest {
    @Test
    public void ipLocal() throws Exception {
       // System.out.println(TreeHoleUtils.ipLocal("119.2.4.202"));
    }

    @Test
    public void systemInfo() throws Exception {
//        System.out.println(TreeHoleUtils.systemInfo());
    }

    @Test
    public void time() throws ParseException {
        String timeb = "2017-01-01 00:01:00";
        String timee = "2017-01-01 01:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateb = format.parse(timeb);
        Date datee = format.parse(timee);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateb);
        while(true){
            calendar.add(Calendar.MINUTE, 5);
            Date date = calendar.getTime();
            this.query(date);
            if(datee.equals(date) || date.after(datee)){
                break;
            }
        }
    }

    private void query(Date date) {
        System.out.println("我查询了"+date+"的数据，并处理");
    }
}
