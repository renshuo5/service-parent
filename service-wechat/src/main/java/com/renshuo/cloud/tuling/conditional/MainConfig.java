package com.renshuo.cloud.tuling.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Lenovo on 2021/3/19.
 */
public class MainConfig {

//    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }


    @Bean
    @Conditional(value = LogCondition.class)
    public Log log(){
        return new Log();
    }

}
