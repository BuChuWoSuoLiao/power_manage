package com.liao.exception.UserException;

import com.liao.exception.baseException.BaseException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.userException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:49
 * @Description: TODO 用户相关的异常
 * @Version: 1.0
 */
public class UserException extends BaseException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
