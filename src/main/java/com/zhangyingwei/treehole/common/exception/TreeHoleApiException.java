package com.zhangyingwei.treehole.common.exception;

/**
 * Created by zhangyw on 2017/4/26.
 */
public class TreeHoleApiException extends Exception {
    public TreeHoleApiException() {
        super();
    }

    public TreeHoleApiException(String message) {
        super(message);
    }

    public TreeHoleApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeHoleApiException(Throwable cause) {
        super(cause);
    }

    protected TreeHoleApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
