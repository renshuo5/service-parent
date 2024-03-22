package com.renshuo.cloud.bean;

/** 
* @description: token值存放的bean 
*
* @author: renshuo 
* @date: 2021/1/29 
*/
public class TokenBean  {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static TokenBean instance;

    //构造器私有化
    private TokenBean(){}

    //方法同步，调用效率低
    public static synchronized TokenBean getInstance(){
        if(instance==null){
            instance=new TokenBean();
        }
        return instance;
    }

}
