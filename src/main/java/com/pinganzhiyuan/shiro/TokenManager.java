package com.pinganzhiyuan.shiro;


import org.apache.shiro.SecurityUtils;

import com.pinganzhiyuan.model.UserBackend;



public class TokenManager {

    /**
     * 获取当前登录的用户User对象
     * @return
     */
    public static UserBackend getToken(){
        UserBackend token = (UserBackend)SecurityUtils.getSubject().getPrincipal();
        return token ;
    }
    
    /**
     * 登录
     * @param user
     * @param rememberMe
     * @return
     */
    public static UserBackend login(UserBackend user,Boolean rememberMe){
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);
        SecurityUtils.getSubject().login(token);
        return getToken();
    }
    
}
