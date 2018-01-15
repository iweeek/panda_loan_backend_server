package com.pinganzhiyuan.controller;


import javax.servlet.http.HttpServletResponse;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.service.TokenService;
import com.pinganzhiyuan.util.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
* token资源的restful控制器
* 
* @author x1ny
* @date 2017年5月22日
*/
@Api(tags = "Token相关接口")
@RestController
@RequestMapping(value="/tokens",produces="application/json;charset=UTF-8")
public class TokenController {
	
//	private int status = 0;

	@Autowired
	private TokenService tokenService;
	
	/**
	* 验证username和password，创建token
	* 
	* @param username 用户名
	* @param password 密码
	* @param expiredHour 过期时间(小时)
	*/
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "创建token", notes = "验证用户名与密码，为用户创建一个用于鉴权的Token")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(
								@ApiParam("学校Id")
								@RequestParam long universityId,
								@ApiParam("用户名")
								@RequestParam String username,
								@ApiParam("密码")
								@RequestParam String password,
								@ApiParam("设备Id")
								@RequestParam String deviceId,
								@ApiParam("有效时间(单位:小时)，不填则默认为1")
								@RequestParam(required=false, defaultValue="1") Integer expiredHour,
								@RequestHeader("User-Agent") String userAgent
								) {
		System.out.println(userAgent);
		
		// 原来的create()方法 与 shiro 的登录顺序应该考虑一下。
		ResponseBody resBody = new ResponseBody();
		int status = tokenService.create(universityId, username, password, expiredHour, deviceId, userAgent, resBody);
		
		
		return ResponseEntity.status(status).body(resBody);
	}
}
