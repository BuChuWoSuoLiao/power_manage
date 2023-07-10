package com.liao.exception.SqlException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.serviceException.dataBaseException
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:46
 * @Description: TODO 数据库删除异常
 * @Version: 1.0
 */
public class DeleteException extends SqlException {
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
