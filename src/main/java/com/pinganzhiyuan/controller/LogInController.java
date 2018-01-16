package com.pinganzhiyuan.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.service.LogInService;

import io.swagger.annotations.ApiOperation;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.SMSLogMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.SMSLog;
@RestController
public class LogInController {
	@Autowired
	private LogInService logInService;

	@Autowired
	private DeviceLogMapper deviceLogMapper;
	 
	@ApiOperation(value = "生成新的用户画像表信息", notes = "生成新的用户画像信息")
    @RequestMapping(value = { "/createLogInStatisticDaily" }, method = RequestMethod.POST)
    public ResponseEntity<?> create() {
		//取所有日志信息

		DeviceLogExample deviceLogExample = new DeviceLogExample();
		deviceLogExample.createCriteria().andUriLike("%" + "panda_loan/tokens" + "%");
		List<DeviceLog> deviceLogs = deviceLogMapper.selectByExample(deviceLogExample);
		
		for (DeviceLog deviceLog : deviceLogs) {
			logInService.createLogInByDeviceLog(deviceLog);
		}
		
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
}
