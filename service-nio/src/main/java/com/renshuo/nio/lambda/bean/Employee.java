package com.renshuo.nio.lambda.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Lenovo on 2023/8/31.
 */
@Data
@NoArgsConstructor
public class Employee {

    private Integer age;

    public String show(){

        return "show";
    }
}
