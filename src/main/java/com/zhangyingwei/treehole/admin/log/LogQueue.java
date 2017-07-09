package com.zhangyingwei.treehole.admin.log;

import com.zhangyingwei.treehole.admin.log.model.LogModel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhangyw on 2017/6/14.
 */
@Component
public class LogQueue {
    private ArrayBlockingQueue<LogModel> queue;

    /**
     * 初始化队列
     */
    public LogQueue() {
        this.queue = new ArrayBlockingQueue(1024);
    }

    /**
     * 入队一条日志
     * @param log
     * @return
     */
    public LogModel push(LogModel log){
        this.queue.add(log);
        return log;
    }

    /**
     * 出队一条日志
     * @return
     */
    public LogModel take() throws InterruptedException {
        return this.queue.take();
    }

    /**
     * 队列大小
     * @return
     */
    public Integer size(){
        return this.queue.size();
    }

    /**
     * 判断队列是否空
     * @return
     */
    public Boolean isEmpty(){
        return this.queue.isEmpty();
    }

    /**
     * 清空队列
     */
    public void clear(){
        this.queue.clear();
    }

}
