package com.zhangyingwei.treehole.common.exception;

/**
 * Created by zhangyw on 2017/4/26.
 */
public class TreeHoleException extends Exception {
    public TreeHoleException() {
        super();
    }

    public TreeHoleException(String message) {
        super(message);
    }

    public TreeHoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeHoleException(Throwable cause) {
        super(cause);
    }

    protected TreeHoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
