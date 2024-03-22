package com.renshuo.cloud.common.model;

import com.renshuo.cloud.enums.ResultStatus;

/**
 * 请求响应结构，参见接口文档
 */
public class ResultMsg {

    /**
     * -1-失败，200成功
     */
    private int code = 200;
    private String msg;
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultMsg(Object data) {
        this.code = ResultStatus.CODE_OK.getCode();
        this.msg = "请求成功";
        this.data = data;
    }

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultMsg success(Object data) {
        return new ResultMsg(data);
    }

    public static ResultMsg fail(Object data) {
        return new ResultMsg(ResultStatus.CODE_EXCEPTION.getCode(), ResultStatus.CODE_EXCEPTION.getMessage(), data);
    }

    public static ResultMsg fail(String msg) {
        return new ResultMsg(ResultStatus.CODE_EXCEPTION.getCode(), msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
