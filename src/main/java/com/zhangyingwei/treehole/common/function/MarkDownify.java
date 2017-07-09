package com.zhangyingwei.treehole.common.function;

import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhangyw
 * @date: 2017/6/1
 * @time: 下午10:19
 * @desc: Markdown 支持 将 Markdown 格式的字符串转换为 HTML 。
 */
public class MarkDownify implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        String markdown = paras[0]+"";
        return TreeHoleUtils.markdown(markdown).getBytes();
    }
}
