package com.zhangyingwei.treehole.common.function;

import org.apache.commons.lang3.StringEscapeUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午4:45
 * @desc:
 */
public class Replace implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String content = paras[0]+"";
        String from = paras[1]+"";
        String to = paras[2] + "";
        return content.replaceAll(from, to);
    }
}
