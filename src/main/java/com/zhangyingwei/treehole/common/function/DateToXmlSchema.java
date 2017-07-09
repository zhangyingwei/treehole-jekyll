package com.zhangyingwei.treehole.common.function;

import com.zhangyingwei.treehole.common.utils.DateUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhangyw
 * @date: 2017/6/1
 * @time: 下午10:19
 * @desc: beetl 自定义方法 时间转换为日期
 */
public class DateToXmlSchema implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String timeStr = paras[0]+"";
        try {
            Date date = DateUtils.formate(timeStr);
            return DateUtils.toXmlSchema(date).getBytes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
