package com.zhangyingwei.treehole.common.function;

import org.beetl.core.Context;
import org.beetl.core.Function;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author: zhangyw
 * @date: 2017/7/9
 * @time: 下午4:45
 * @desc: 数组转换为句子 将数组转换为句子，列举标签时尤其有用。
 */
public class ArrayToSentenceString implements Function {
    @Override
    public Object call(Object[] paras, Context context) {
        List list = (List) paras[0];
        if(list.size()==0){
            return "".getBytes();
        }
        return list.toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "").getBytes();
    }
}
