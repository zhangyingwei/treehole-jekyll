package com.zhangyingwei.treehole.log.filter;

import com.zhangyingwei.treehole.log.model.LogModel;

/**
 * Created by zhangyw on 2017/6/16.
 */
public interface LogFilter {
    boolean access(LogModel log);
}
