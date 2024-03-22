package com.renshuo.cloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CsrfInfo {

    /**
     * uuid 无意义
     **/
    private String uuid;
    /**
     * 生成时间， 毫秒级别
     **/
    private long time;
    /**
     * 有效时间, 毫秒级别
     **/
    private long validTime;
}
