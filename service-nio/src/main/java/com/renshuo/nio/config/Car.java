package com.renshuo.nio.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Lenovo on 2023/6/25.
 */
@Data
@Component
public class Car {
    private String model;
    private String type;
    private String name;
}
