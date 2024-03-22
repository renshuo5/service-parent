package com.renshuo.cloud.common.exception;

/**
 * 业务异常类
 * 通用的异常业务类，一般是在业务层直接抛出异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误代码
     **/
    private int code = -1;

    public int getCode() {
        return code;
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
