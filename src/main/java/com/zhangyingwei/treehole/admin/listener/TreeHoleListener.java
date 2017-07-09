package com.zhangyingwei.treehole.admin.listener;

import com.zhangyingwei.treehole.admin.log.LogHandler;
import com.zhangyingwei.treehole.common.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/14.
 */
@Component
public class TreeHoleListener implements ApplicationListener {
    private Logger logger = LoggerFactory.getLogger(TreeHoleListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //我也不知道为什么，不加这一句方法就会被执行三次，如果加上就执行一次，那就加上喽
        ContextRefreshedEvent event = (ContextRefreshedEvent) applicationEvent;
        logger.info("初始化 TreeHole日志收集器");
        LogHandler logHandler = ApplicationContextProvider.getBean("logHandler", LogHandler.class);
        logHandler.start();
        logger.info("TreeHole 日志收集器初始化成功");
    }
}
