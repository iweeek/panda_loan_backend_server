package com.pinganzhiyuan.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.LogInStatisticDailyMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.DeviceExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.LogInStatisticDaily;
import com.pinganzhiyuan.model.LogInStatisticDailyExample;
import com.pinganzhiyuan.model.LogInStatisticDailyExample.Criteria;
import com.pinganzhiyuan.model.UserPortrayal;
import com.pinganzhiyuan.service.LogInService;
@Service
public class LogInServiceImpl implements LogInService {
	
	@Autowired
	private LogInStatisticDailyMapper logInStatisticDailyMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;
	@Override
	public void createLogInByDeviceLog(DeviceLog deviceLog) {
		// TODO Auto-generated method stub
		
		LogInStatisticDaily logIn = new LogInStatisticDaily();
		List<DeviceLog> deviceLogs = deviceLogMapper.getDeviceLogsListByDeviceId(deviceLog);
		if (deviceLogs.size() >= 2) {
			logIn.setLogInTime(deviceLog.getCreatedAt());
			DeviceExample example = new DeviceExample();
			example.createCriteria().andDeviceIdEqualTo(deviceLog.getDeviceId());
			logIn.setDeviceId(deviceMapper.selectByExample(example).get(0).getId());
			logIn.setUserId(deviceLogs.get(1).getUserId());
			logIn.setStatisticDate(deviceLog.getCreatedAt());
			
			LogInStatisticDailyExample loginExample = new LogInStatisticDailyExample();
			Criteria criteria = loginExample.createCriteria();
			criteria.andUserIdEqualTo(deviceLogs.get(1).getUserId());
			criteria.andLogInTimeEqualTo(deviceLog.getCreatedAt());
			if (logInStatisticDailyMapper.selectByExample(loginExample).size() != 0) {
				logIn.setId(logInStatisticDailyMapper.selectByExample(loginExample).get(0).getId());
				logIn.setCreatedAt(logInStatisticDailyMapper.selectByExample(loginExample).get(0).getCreatedAt());
				logInStatisticDailyMapper.updateByPrimaryKey(logIn);
			} else {
				logIn.setCreatedAt(new Date());
				logInStatisticDailyMapper.insert(logIn);
			}	
		}
	}

}
