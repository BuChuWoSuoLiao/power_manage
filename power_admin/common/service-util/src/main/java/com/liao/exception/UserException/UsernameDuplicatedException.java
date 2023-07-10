package com.liao.exception.UserException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.userException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:51
 * @Description: 用户名已存在异常
 * @Version: 1.0
 */
public class UsernameDuplicatedException extends UserException {
    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
