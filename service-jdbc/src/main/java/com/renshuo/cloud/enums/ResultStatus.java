package com.renshuo.cloud.enums;

public enum ResultStatus {

    CODE_OK(200, "OK"),
    CODE_BUSINESS_EXCEPTION(-1, "业务异常"),
    CODE_EXCEPTION(-2, "系统内部异常"),
    CODE_UN_AUTHENTICATION(1000, "用户未登录"),
    CODE_UN_AUTHORIZED(1001, "请求未授权");

    private final int code;
    private final String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
