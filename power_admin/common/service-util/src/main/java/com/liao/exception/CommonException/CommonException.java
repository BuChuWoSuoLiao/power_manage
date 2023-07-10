package com.liao.exception.CommonException;

/**
 * @BelongsPackage: com.liao.exception.CommonException
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-29  23:47
 * @Version: 1.0
 */
public class CommonException extends RuntimeException{
    public CommonException() {
        super();
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
