package com.renshuo.cloud.user.dao;

import com.renshuo.cloud.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
* @description: 用户
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Mapper
public interface UserMapper {


    public Set<String> findUserPermissions(String userId);

//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    User findById(String id);
//
//    /**
//     * 查询指定行数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    List<User> findAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
//
//
//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param user 实例对象
//     * @return 对象列表
//     */
//    List<User> findAll(User user);
//
//    /**
//     * 新增数据
//     *
//     * @param user 实例对象
//     * @return 影响行数
//     */
//    int insert(User user);
//
//    /**
//     * 修改数据
//     *
//     * @param user 实例对象
//     * @return 影响行数
//     */
//    int update(User user);
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 影响行数
//     */
//    int deleteById(Integer id);


}
