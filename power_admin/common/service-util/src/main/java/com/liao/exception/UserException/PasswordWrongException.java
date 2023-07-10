package com.liao.exception.UserException;

/**
 * @BelongsPackage: com.liao.exception.UserException
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-07-04  00:15
 * @Version: 1.0
 */
public class PasswordWrongException extends UserException{

    public PasswordWrongException() {
        super();
    }

    public PasswordWrongException(String message) {
        super(message);
    }

    public PasswordWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordWrongException(Throwable cause) {
        super(cause);
    }

    protected PasswordWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
