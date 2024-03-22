package com.renshuo.cloud.common;

/**
 *
 * 请求响应结构，参见接口文档
 */
public class ResultMsg {

    /**
     * 0-失败，1成功
     */
    private int code = 0;
    private String msg;
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultMsg(Object data) {
        this.code = 0;
        this.msg = "请求成功";
        this.data = data;
    }

    public ResultMsg(int code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultMsg success(Object data) {
        return new ResultMsg(data);
    }

    public static ResultMsg fail(Object data) {
        return new ResultMsg(-1,"请求失败",data);
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
