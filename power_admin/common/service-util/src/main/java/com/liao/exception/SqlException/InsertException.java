package com.liao.exception.SqlException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.serviceException.dataBaseException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:39
 * @Description: TODO 数据库插入异常
 * @Version: 1.0
 */
public class InsertException extends SqlException {
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
