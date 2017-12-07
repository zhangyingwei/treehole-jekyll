package com.zhangyingwei.treehole.common.exception;

/**
 * Created by zhangyw on 2017/12/7.
 */
public class TreeHoleOutOfPageException extends Exception {
    public TreeHoleOutOfPageException() {
        super();
    }

    public TreeHoleOutOfPageException(String message) {
        super(message);
    }

    public TreeHoleOutOfPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeHoleOutOfPageException(Throwable cause) {
        super(cause);
    }

    protected TreeHoleOutOfPageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
