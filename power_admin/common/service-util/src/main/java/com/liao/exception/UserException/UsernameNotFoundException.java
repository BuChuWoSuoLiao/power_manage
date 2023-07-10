package com.liao.exception.UserException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.userException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:49
 * @Description: 用户不存在异常
 * @Version: 1.0
 */
public class UsernameNotFoundException extends UserException {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
