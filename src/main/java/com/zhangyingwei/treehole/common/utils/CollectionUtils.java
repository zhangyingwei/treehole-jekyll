package com.zhangyingwei.treehole.common.utils;

import java.util.*;

/**
 * Created by zhangyw on 2017/6/16.
 * Map 工具类
 */
public class CollectionUtils {


    /**
     * 按照 key 排序
     * @param map
     */
    public static Map sortByKey(Map map){
        Set keys = map.keySet();
        List list = new ArrayList(keys);
        Collections.sort(list);
        Map resultMap = new TreeMap();
        for (Object k : list) {
            resultMap.put(k, map.get(k));
        }
        return resultMap;
    }
}
