package com.renshuo.cloud.tuling.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Created by Lenovo on 2021/3/19.
 */

@Configuration
public class MainConfig {

    @Bean
    @Lazy
//    @Scope(scopeName="prototype")
    public Person person(){
        return new Person();
    }
}
