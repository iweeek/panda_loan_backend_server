package com.pinganzhiyuan.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.mapper.UserPortrayalMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPortrayalMapper userPortrayalMapper;
	
	@Autowired
	private UserMapper userMapper;
	@ApiOperation(value = "生成新的用户画像表信息", notes = "生成新的用户画像信息")
    @RequestMapping(value = { "/createUserPortrayal" }, method = RequestMethod.POST)
    public ResponseEntity<?> create() {
		//取所有日志信息
		List<User> users = userMapper.selectByExample(null);
		
		for (User user : users) {
			userService.createUserPortrayalByUser(user);
		}
		
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
}
