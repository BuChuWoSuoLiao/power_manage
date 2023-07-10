package com.liao.exception.SqlException;

/**
 * @BelongsPackage: com.liao.exception.SqlException
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-29  23:47
 * @Version: 1.0
 */
public class SqlException extends RuntimeException{
    public SqlException() {
        super();
    }

    public SqlException(String message) {
        super(message);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlException(Throwable cause) {
        super(cause);
    }

    protected SqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
