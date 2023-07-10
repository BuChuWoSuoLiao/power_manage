package com.liao.exception.baseException;

/**
 * @BelongsPackage: com.liao.exception.baseException
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-29  23:36
 * @Version: 1.0
 */
public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
