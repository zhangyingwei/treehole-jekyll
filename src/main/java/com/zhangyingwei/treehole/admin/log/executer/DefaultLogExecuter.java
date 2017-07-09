package com.zhangyingwei.treehole.admin.log.executer;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.exception.TreeHoleLogException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/14.
 * 日志处理类
 */
@Component
public class DefaultLogExecuter implements LogExecuter {

    /**
     * 处理日志的方法
     * @param log
     * @return
     */
    @Override
    public LogModel execute(LogModel log) throws TreeHoleLogException {
        //获取ip地址的位置信息
        if(StringUtils.isNotEmpty(log.getIp())){
            try {
                if (log.getUri().startsWith("/articles/")) {
                    String[] uris = log.getUri().split("/");
                    String id = uris[uris.length - 1];
                    log.setAction(log.getAction() + "[" + id + "]");
                }
            } catch (Exception e) {
                throw new TreeHoleLogException("获取文章编号错误");
            }
        }
        return log;
    }
}
