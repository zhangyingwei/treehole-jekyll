package com.zhangyingwei.treehole.admin.log;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.common.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/15.
 */
@Component("logHandler")
public class LogHandler {
    private int threadNum = 1;
    private Logger logger = LoggerFactory.getLogger(LogHandler.class);
    private LogQueue queue;
    public void start(int threadNum){
        this.threadNum = threadNum;
        this.initQueue();
        this.run();
    }
    public void start(){
        this.initQueue();
        this.run();
    }

    private void initQueue() {
        this.queue = new LogQueue();
    }

    public void produceLog(LogModel log){
        this.queue.push(log);
    }

    private void run(){
        for (int i = 0; i < threadNum; i++) {
            LogTask logTask = ApplicationContextProvider.getBean("logTask", LogTask.class);
            logTask.setQueue(queue);
            Thread thread = new Thread(logTask);
            thread.start();
            logger.info("启动日志收集线程:"+thread.getId());
        }
    }
}
