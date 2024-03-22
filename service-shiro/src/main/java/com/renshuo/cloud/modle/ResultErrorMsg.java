package com.renshuo.cloud.modle;

import lombok.Data;

@Data
public class ResultErrorMsg {

    /** 错误唯一编码 **/
    private String errorCode;
    /** 详细错误 **/
    private String detailException;
    private int state;
}
