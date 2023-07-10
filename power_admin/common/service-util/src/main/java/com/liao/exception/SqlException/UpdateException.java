package com.liao.exception.SqlException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.serviceException.dataBaseException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:39
 * @Description: 数据库更改异常
 * @Version: 1.0
 */
public class UpdateException extends SqlException {
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
