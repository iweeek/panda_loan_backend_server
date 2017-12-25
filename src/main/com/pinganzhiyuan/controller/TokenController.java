package com.pinganzhiyuan.controller;

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
import com.pinganzhiyuan.model.Captcha;
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
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建token", notes = "验证用户名与密码，为用户创建一个用于鉴权的Token")
    @RequestMapping(value = { "/tokens" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("用户名") @RequestParam String username,
            @ApiParam("密码") @RequestParam String password, @ApiParam("盐值") @RequestParam String salt,
            @ApiParam("有效时间(单位:小时)，不填则默认为1") @RequestParam(required = false, defaultValue = "1") Integer expiredHour,
            HttpServletRequest request, HttpSession httpSession, @RequestParam String key) {
        Captcha captcha = new Captcha();
        Boolean isPassed = captchaService.verifyCaptchaIsPassed(request, captcha, key);
        if (isPassed) {
            ResponseBody body = new ResponseBody();
            int status = tokenService.create(username, password, salt, expiredHour, body, httpSession);
            if (status == 0) {
                // 删除本次的验证码
                if (captcha != null) {
                    captchaMapper.deleteByPrimaryKey(captcha.getId());
                }
                return ResponseEntity.status(HttpServletResponse.SC_OK).body(body);
            } else {
//                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(isPassed);
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(isPassed);
        }
    }
}
