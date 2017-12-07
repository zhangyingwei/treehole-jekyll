package com.zhangyingwei.treehole.log.executer;

import com.zhangyingwei.treehole.common.config.TreeHoleConfig;
import com.zhangyingwei.treehole.log.model.LogModel;
import com.zhangyingwei.treehole.common.exception.TreeHoleLogException;
import com.zhangyingwei.treehole.common.utils.TreeHoleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/14.
 * 日志处理类
 */
@Component
public class ArticleIdLogExecuter implements LogExecuter {

    @Autowired
    private TreeHoleConfig treeHoleConfig;

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
                log.setIp_location(TreeHoleUtils.ipLocal(log.getIp(),treeHoleConfig));
            } catch (Exception e) {
                throw new TreeHoleLogException("获取 ip 物理位置错误: " + log.getIp());
            }
        }
        return log;
    }
}
