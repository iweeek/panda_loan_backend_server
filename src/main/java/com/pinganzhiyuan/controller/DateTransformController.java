package com.pinganzhiyuan.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.service.DateTransformService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DateTransformController {
	
	@Autowired
	private DateTransformService dateTransformService;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;
	
	@ApiOperation(value = "统计流量转化信息", notes = "流量转化记录")
    @RequestMapping(value = { "/dateTransform" }, method = RequestMethod.POST)
    public ResponseEntity<?> create() {
		//取所有日志信息
		List<DeviceLog> deviceLogs = deviceLogMapper.selectByUserIdAndSid();
		
		dateTransformService.createDateTransformByDeviceLog(deviceLogs);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
}
