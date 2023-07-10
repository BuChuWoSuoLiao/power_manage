package com.liao.exception.CommonException;

/**
 * @BelongsProject: blogs_java
 * @BelongsPackage: com.liao.blogs.exception.commonExcption
 * @Author: YunYang Liao
 * @CreateTime: 2023-04-17  21:52
 * @Description: TODO 数据错误异常
 * @Version: 1.0
 */
public class DataNotFontException extends CommonException {
    public DataNotFontException() {
        super();
    }

    public DataNotFontException(String message) {
        super(message);
    }

    public DataNotFontException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFontException(Throwable cause) {
        super(cause);
    }

    protected DataNotFontException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
