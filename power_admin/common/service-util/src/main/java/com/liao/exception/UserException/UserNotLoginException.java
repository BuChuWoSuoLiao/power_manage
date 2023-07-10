package com.liao.exception.UserException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.userException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  22:59
 * @Description: 用户未登录异常
 * @Version: 1.0
 */
public class UserNotLoginException extends UserException {
    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String message) {
        super(message);
    }

    public UserNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotLoginException(Throwable cause) {
        super(cause);
    }

    protected UserNotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
