package com.zhangyingwei.treehole.common.function;

import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午4:45
 * @desc:
 */
public class ObjectIs implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        Object from = paras[0];
        Object  to = paras[1];
        return from.equals(to);
    }
}
