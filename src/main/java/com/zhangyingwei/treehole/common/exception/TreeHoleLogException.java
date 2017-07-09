package com.zhangyingwei.treehole.common.exception;

/**
 * Created by zhangyw on 2017/4/26.
 */
public class TreeHoleLogException extends Exception {
    public TreeHoleLogException() {
        super();
    }

    public TreeHoleLogException(String message) {
        super(message);
    }

    public TreeHoleLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeHoleLogException(Throwable cause) {
        super(cause);
    }

    protected TreeHoleLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
