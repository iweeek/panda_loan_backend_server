package com.pinganzhiyuan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.CaptchaMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Captcha;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;
import com.pinganzhiyuan.service.CaptchaService;
import com.pinganzhiyuan.service.TokenService;
import com.pinganzhiyuan.util.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
public class TokenController {
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    CaptchaMapper captchaMapper;
    
    @Autowired
    UserMapper userMapper;
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建token", notes = "验证用户名与密码，为用户创建一个用于鉴权的Token")
    @RequestMapping(value = { "/tokens" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("用户名（电话号码）") @RequestParam String username, 
            @ApiParam("图形验证码的key")@RequestParam(required = false) String keyImageCapt,
            @ApiParam("图形验证码")@RequestParam(required = false) String imageCapt, 
            @ApiParam("短信验证码的key")@RequestParam String keySMSCapt,
            @ApiParam("短信验证码")@RequestParam String smsCapt) {
        
        Boolean isPassed = false;
        
        ResponseBody resBody = new ResponseBody<String>();
        
        String msg;
        
        if (imageCapt != null && keyImageCapt != null) { 
            isPassed = captchaService.verifyCaptcha(imageCapt, keyImageCapt);
            if (!isPassed) {
                resBody.statusMsg = "图形验证码不正确";
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(resBody);
            }
        }
        
        isPassed = captchaService.verifyCaptcha(smsCapt, keySMSCapt);
        if (isPassed) {
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(username);
            List<User> list = userMapper.selectByExample(example);
            if (list.size() > 0) {
                resBody.statusMsg = "登录成功";
                resBody.obj1 = list.get(0);
                return ResponseEntity.status(HttpServletResponse.SC_OK).body(resBody);
            }
           
            User user = new User();
            user.setUsername(username);
            user.setPassword("");
            user.setRegistTime((new Date()).getTime());
            userMapper.insert(user);
            
            resBody.statusMsg = "登录成功";
            resBody.obj1 = user;
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(resBody);
        } else {
            resBody.statusMsg = "短信验证码不正确";
            return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(resBody);
        }
    }
}
