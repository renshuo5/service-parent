package com.renshuo.cloud.service;

import com.github.pagehelper.PageInfo;
import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.dao.UserMapper;
import com.renshuo.cloud.domain.User;
import com.renshuo.cloud.reqbean.PagerInfo;
import com.renshuo.cloud.reqbean.PagerResult;
import com.renshuo.cloud.service.impl.BaseService;
import com.renshuo.cloud.util.PagerInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* @description: 用户接口
*
* @author: renshuo 
* @date: 2020/12/4 
*/
@Service
@Mybatis(namespace = "com.renshuo.cloud.dao.UserMapper")
public class UserService extends BaseService {


    public PageInfo list(PagerInfo pagerInfo){

        Map<String,Object> param  = PagerInfoUtil.pageInfoToMap(pagerInfo);

        return this.findPagerModel("pagerModel",param,pagerInfo.getStart(),pagerInfo.getLimit());


    }





      //未添加baseservice时候代码开发模式
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    public User findUserByUsername(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        return this.unique("findUserByUsername",username);
    }


    public Set<String> findUserPermissions(Integer userId){
        return userMapper.findUserPermissions(userId);
    }

//
//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit 查询条数
//     * @return 对象列表
//     */
//    public List<User> findAllByLimit(int offset, int limit) {
//        return this.userMapper.findAllByLimit(offset, limit);
//    }
//    /**
//     * 查询多条数据
//     *
//     * @param user 对象信息
//     * @return 对象列表
//     */
//    public List<User> findAllByUser(User user) {
//        return this.userMapper.findAll(user);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    public User insert(User user) {
//        this.userMapper.insert(user);
//        return user;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param user 实例对象
//     * @return 实例对象
//     */
//    public User update(User user) {
//        this.userMapper.update(user);
//        return this.findById(String.valueOf(user.getId()));
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    public boolean deleteById(Integer id) {
//        return this.userMapper.deleteById(id) > 0;
//    }


}
