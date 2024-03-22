package com.renshuo.cloud.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.service.IBaseService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 基础service服务
 * @author: renshuo
 * @date: 2020/12/7
 */
public class BaseService implements IBaseService, InitializingBean {


    protected String namespace;


    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public <T> int insert(T o) {
        return sqlSessionTemplate.insert(getSqlId("insert"), o);
    }

    @Override
    public <T> int insert(String sqlId, T o) {
        return sqlSessionTemplate.insert(getSqlId(sqlId), o);
    }

    @Override
    public <T> int update(T o) {
        return sqlSessionTemplate.update(getSqlId("update"), o);
    }

    @Override
    public <T> int update(String sqlId, T o) {
        return sqlSessionTemplate.update(getSqlId(sqlId), o);
    }

    @Override
    public <T> int updateByMap(Map<String, Object> params) {
        return this.sqlSessionTemplate.update(this.getSqlId("update"), params);
    }

    @Override
    public int delete(String id) {
        return sqlSessionTemplate.delete(getSqlId("delete"), id);
    }

    @Override
    public int delete(String sqlId, Object object) {
        return sqlSessionTemplate.delete(getSqlId(sqlId), object);
    }

    @Override
    public <T> T findById(String id) {
        return sqlSessionTemplate.selectOne(getSqlId("findById"),id);
    }

    @Override
    public <T> T findByMap(Map<String, Object> params) {
        return sqlSessionTemplate.selectOne(getSqlId("findById"),params);
    }

    @Override
    public <T> List<T> findBySqlId(String sqlId, Object params) {
        return sqlSessionTemplate.selectList(getSqlId(sqlId), params);
    }

    @Override
    public PageInfo findPagerModel(String selectId, Map<String, Object> params, int start, int limit) {
        start = start *limit;
        PageRowBounds rowBounds = new PageRowBounds(start, limit);
        List list = sqlSessionTemplate.selectList(getSqlId(selectId), params, rowBounds);
        PageInfo pageInfo = new PageInfo(list);
//        PagerResult pm = new PagerResult();
//        pm.setData(list);
//        pm.setPageInfo(pageInfo);
//        pm.setTotal(rowBounds.getTotal());
        return pageInfo;
    }

    @Override
    public <T> T unique(String sqlId, Object params) {
        return sqlSessionTemplate.selectOne(getSqlId(sqlId), params);
    }

    @Override
    public <T> T unique(String sqlId) {
        return sqlSessionTemplate.selectOne(getSqlId(sqlId));
    }

    private void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * 获取service中的mapper命名空间中的sqlid
     * @param sqlId
     * @return
     */
    public String getSqlId(String sqlId) {
        String sid = sqlId;
        if (sqlId.contains(".")) {
            String[] arr = sqlId.split(".");
            sid = arr[1];
        }
        return String.format("%s.%s", namespace, sid);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.namespace == null) {
            String simpleName = this.getClass().getSimpleName();
            boolean mapper = this.getClass().isAnnotationPresent(Mybatis.class);
            if(mapper){
                Mybatis annotation = (Mybatis) this.getClass().getAnnotation(Mybatis.class);
                this.setNamespace(annotation.namespace());
            }else{
                this.setNamespace(simpleName);
            }
        }
    }
}
