package com.zhangyingwei.treehole.common.function;

import com.zhangyingwei.treehole.common.utils.DateUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.ParseException;
import java.util.Date;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午4:45
 * @desc:
 */
public class DateToString implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String timeStr = paras[0]+"";
        try {
            Date date = DateUtils.formate(timeStr);
            return DateUtils.formate("MMM d",date).getBytes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
