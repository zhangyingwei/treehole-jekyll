package com.zhangyingwei.treehole.common.function;

import com.zhangyingwei.treehole.common.utils.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
public class XmlEscape implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String content = paras[0]+"";
        return StringEscapeUtils.escapeXml(content).getBytes();
    }
}
