package com.pinganzhiyuan.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceStatisticalData;
import com.pinganzhiyuan.service.DeviceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;
	
	
	@ApiOperation(value = "生成新的设备表信息", notes = "生成新的设备表信息")
    @RequestMapping(value = { "/createDevice" }, method = RequestMethod.POST)
    public ResponseEntity<?> create() {
		//取所有日志信息
		List<DeviceLog> deviceLogs = deviceLogMapper.selectByExample(null);
		
		deviceService.createDeviceData(deviceLogs);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	@ApiOperation(value = "设备统计数据", notes = "日新增设备统计数据")
    @RequestMapping(value = { "/getDeviceStatisticalData" }, method = RequestMethod.POST)
    public ResponseEntity<?> get() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		try {
			startDate = sdf.parse("2017-12-29");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DAY_OF_MONTH, 1);// +1天
        Date endDate = c.getTime();
        
        deviceService.deviceStatisticDaily(startDate,endDate);       
		return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	@ApiOperation(value = "设备统计数据", notes = "日新增设备统计数据")
    @RequestMapping(value = { "/setDeviceStatisticalTime" }, method = RequestMethod.POST)
    public ResponseEntity<?> set() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date();
		try {
			startDate = sdf.parse("2017-12-25 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0;i < 12;i ++) {
        	Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.HOUR, 2);// +2小时
            Date endDate = c.getTime(); 
            deviceService.deviceStatisticTime(Timestamp.valueOf(sdf.format(startDate)),Timestamp.valueOf(sdf.format(endDate)));
            startDate = endDate;
        }      
		return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	
}
