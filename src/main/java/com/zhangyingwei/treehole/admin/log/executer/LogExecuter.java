package com.zhangyingwei.treehole.admin.log.executer;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.exception.TreeHoleLogException;

/**
 * Created by zhangyw on 2017/6/15.
 */
public interface LogExecuter {
    LogModel execute(LogModel log) throws TreeHoleLogException;
}
