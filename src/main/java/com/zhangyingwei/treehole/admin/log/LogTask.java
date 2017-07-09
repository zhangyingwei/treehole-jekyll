package com.zhangyingwei.treehole.admin.log;

import com.zhangyingwei.treehole.admin.log.executer.LogExecuter;
import com.zhangyingwei.treehole.admin.log.model.LogModel;
import com.zhangyingwei.treehole.admin.log.service.LogService;
import com.zhangyingwei.treehole.common.exception.TreeHoleException;
import com.zhangyingwei.treehole.common.exception.TreeHoleLogException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyw on 2017/6/14.
 * 处理日志的线程
 */
@Component("logTask")
@Scope("prototype")
public class LogTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(LogTask.class);

    private LogQueue queue;
    @Autowired
    private LogExecuter defaultLogExecuter;
    @Autowired
    private LogService logService;

    public LogTask() {}

    public LogQueue getQueue() {
        return queue;
    }

    public void setQueue(LogQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                LogModel log = this.queue.take();
                this.defaultLogExecuter.execute(log);
                this.logService.addLog(log);
                logger.info("记录日志成功");
            } catch (InterruptedException e) {
                logger.info(e.getLocalizedMessage());
            } catch (TreeHoleLogException e) {
                logger.info(e.getLocalizedMessage());
            } catch (TreeHoleException e) {
                logger.info(e.getLocalizedMessage());
            }
        }
    }
}
