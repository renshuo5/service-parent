package com.renshuo.cloud.common.model;

import lombok.Data;

@Data
public class ResultErrorMsg extends ResultMsg{

    /** 错误唯一编码 **/
    private String errorCode;
    /** 详细错误 **/
    private String detailException;
}
