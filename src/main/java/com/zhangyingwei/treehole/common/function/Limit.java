package com.zhangyingwei.treehole.common.function;

import org.apache.commons.lang3.StringEscapeUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午4:45
 * @desc:
 */
public class Limit implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        List contents = (List) paras[0];
        Integer size = Integer.parseInt(paras[1]+"");
        if(contents.size()<=size){
            return contents;
        }
        return contents.subList(0,size);
    }
}
