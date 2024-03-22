package com.renshuo.cloud.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Lenovo on 2021/3/10.
 */
@Mapper
@Component
public interface OrderMapper {


    @Insert("INSERT INTO order (name, price) VALUES (#{name}, #{price}); ")
    int insert(@Param("name")String name, @Param("price")BigDecimal price);
}
