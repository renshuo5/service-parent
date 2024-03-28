package com.renshuo.cloud.sys.service;

import com.renshuo.cloud.annation.Mybatis;
import com.renshuo.cloud.common.exception.BusinessException;
import com.renshuo.cloud.common.model.ResultMsg;
import com.renshuo.cloud.config.RedisCache;
import com.renshuo.cloud.sys.model.*;
import com.renshuo.cloud.user.model.LoginModel;
import com.renshuo.cloud.util.CommonUtil;
import com.renshuo.cloud.util.CookieUtils;
import com.renshuo.cloud.util.JwtUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: renshuo
 * @date: 2024/3/18
 */
@Service
@Slf4j
@Mybatis(namespace = "com.renshuo.cloud.user.dao.UserMapper")
public class LoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuLkService roleMenuLkService;

    @Value("${jwtSecret:salt}")
    private String secret;

    private static final String LOGIN_REDIS_KEY_PREFIX = "LOGIN_USER_INFO_";


    public ResultMsg login(LoginModel loginModel) {

        String csrf = CommonUtil.getCsrf();
        String code = redisCache.getCacheObject("randCode_" + csrf);
        log.info("验证码--" + code);

        if (!loginModel.getValidCode().equalsIgnoreCase(code)) {
            throw new BusinessException("验证码输入不正确");
        }

        Map<String, Object> resultMap = new HashMap<>();

        //todo 判断用户信息是否正确进行登录，用户实体信息进行json字符串后jwt加密设置为token
        String userId = "1";
        String userName = "renshuo";
        long now = System.currentTimeMillis();

        long timeout = NumberUtils.toLong("10", 60);

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setCreateTime(now);
        tokenInfo.setUsername(userName);
        tokenInfo.setTimeout(timeout);

        String token = JwtUtil.createJwt(tokenInfo, secret, -1);
        resultMap.put("token", token);
        //1是网页登录，2是app登录、3是小程序登录
        resultMap.put("loginType", 1);
        //登入成功后，redis中存入对应的token信息
        String redisKey = LOGIN_REDIS_KEY_PREFIX + tokenInfo.getUserId() + "_" + tokenInfo.getCreateTime();
        redisCache.setCacheMapValue(redisKey, "token", token);
        redisCache.setCacheMapValue(redisKey, "lastAccessTime", tokenInfo.getCreateTime() + "");

        CookieUtils.getResponse().addHeader("Authorization", "Bearer " + token);
        return ResultMsg.success(resultMap);

    }

    public void logout() {
        try {
            String token = CookieUtils.getCookie("R_SESS");
            token = token.replaceAll(" ", "+");
            TokenInfo tokenInfo = JwtUtil.getTokenInfo(token, secret);
            String redisKey = LOGIN_REDIS_KEY_PREFIX + tokenInfo.getUserId() + "_" + tokenInfo.getCreateTime();
            redisCache.delCacheMapValue(redisKey, "token", "lastAccessTime");
            //从redis中清除
            CookieUtils.removeCookie("CSRF");
            CookieUtils.removeCookie("R_SESS");
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessException("登出报错");
        }
    }


    /**
     * 获取菜单信息
     *
     * @return
     */
    public ResultMsg init() {

        //todo 获取用户的当前roleId来查询分配的菜单，和首页显示什么页面 homeInfoModel.setHref

        InitModel initModel = new InitModel();
        LogoInfoModel logoInfoModel = new LogoInfoModel();
        logoInfoModel.setTitle("renshuo后台");
        logoInfoModel.setImage("images/logo.png");
        logoInfoModel.setHref("");

        HomeInfoModel homeInfoModel = new HomeInfoModel();
        homeInfoModel.setTitle("首页一");
        homeInfoModel.setHref("page/welcome-1.html?t=1");


        MenuInfoModel menuInfoModel = new MenuInfoModel();
        /**
         *
         * "title": "常规管理",
         "icon": "fa fa-address-book",
         "href": "",
         "target": "_self",
         "child": [
         *
         */
        List<MenuInfoModel> menuTree = menuInfoService.getMenuTree();

        initModel.setHomeInfo(homeInfoModel);
        initModel.setLogoInfo(logoInfoModel);
        initModel.setMenuInfo(menuTree);

        return ResultMsg.success(initModel);
    }


    /**
     * 获取个人信息
     *
     * @return
     */
    public Map<String, Object> me() {
        Map<String, Object> result = new HashMap<>();
        String curRoleId ="1556bc05499a45c493b69a150ed8fd41";
        result.put("curRoleIsValid", 1);
        result.put("curRoleIsValidName", "是");
        result.put("curRoleId", curRoleId);
        String indexUrl = roleService.findIndexUrl(curRoleId);
        if (StringUtil.isEmpty(indexUrl)) {
            result.put("indexUrl", "");
        } else {
            result.put("indexUrl", indexUrl);
        }

        //获取角色的按钮权限菜单code
        List<String> btnCodes = roleMenuLkService.queryMenuCodeByRoleId(curRoleId);

        result.put("btnPermission", btnCodes);

        return result;
    }
}
