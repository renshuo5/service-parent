package com.renshuo.cloud.service;

import com.renshuo.cloud.reqbean.PagerResult;

import java.util.List;
import java.util.Map;

/**
 * @description: 基础公共服务接口
 * @author: renshuo
 * @date: 2020/12/5
 */
public interface IBaseService {

    <T> int insert(T var1);

    <T> int insert(String sqlId, T var1);


    <T> int update(T var1);

    <T> int update(String sqlId, T var1);

    /**
     * 删除记录
     *
     * @param id
     */
    int delete(String id);

    /**
     *
     */
    int delete(String sqlId, Object object);

    /**
     * 根据ID查询
     *
     * @param id
     * @param <T>
     * @return
     */
    <T> T findById(String id);

    /**
     * 根据ID查询
     *
     * @param params
     * @param <T>
     * @return
     */
    <T> T findByMap(Map<String, Object> params);


    /**
     * 根据参数查询列表
     *
     * @param sqlId
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> findBySqlId(String sqlId, Object params);


    /**
     * 根据分页返回信息
     *
     * @param selectId
     * @param params
     * @param start
     * @param limit
     * @return
     */
    PagerResult findPagerModel(String selectId, Map<String, Object> params, int start, int limit);


    /**
     * 查找单个实体
     *
     * @param sqlId
     * @param params
     * @param <T>
     * @return
     */
    <T> T unique(String sqlId, Object params);

    /**
     * 查找单个实体
     *
     * @param sqlId
     * @param <T>
     * @return
     */
    <T> T unique(String sqlId);


}
