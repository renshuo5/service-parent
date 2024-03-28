package com.renshuo.cloud.common.model;

import com.renshuo.cloud.enums.ResultStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Lenovo on 2024/3/28.
 */
@Getter
@Setter
public class ResultPageMsg extends ResultMsg {

    private int code;
    private long count;

    public ResultPageMsg(long count, Object data) {
        super(data);
        this.code = 0;
        this.count = count;
    }

    public static ResultPageMsg success(long count, Object data) {
        return new ResultPageMsg(count, data);
    }

}
