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
 * @desc: 统计给定段落的文字个数
 */
public class NumberOfWords implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String content = paras[0]+"";
        return content.length();
    }
}
