package com.pinganzhiyuan.timer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;
import com.pinganzhiyuan.model.DeviceLogExample.Criteria;
import com.pinganzhiyuan.service.DeviceService;
import com.pinganzhiyuan.service.LogInService;
import com.pinganzhiyuan.service.UserService;

@Component
public class NewHourlyStatisticTask {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private LogInService logInService;
	

	//@Scheduled(cron = "0 1 1 * * ?")
	public void job(){
		//根据当前时间截取任务开始时间和结束时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		int hour = Integer.valueOf(sdf.format(date).substring(0, sdf.format(date).indexOf(":")));
		Date startTime = new Date();
		Date endTime = new Date();
		if ( hour == 0 || hour == 1 ) {
			DateTime dateTime = new DateTime().withMillisOfDay(0).minusDays(1);
			startTime = dateTime.toDate();
			Calendar c = Calendar.getInstance();
            c.setTime(startTime);
            c.add(Calendar.HOUR, (hour + 22));// +22小时 
            startTime = c.getTime();
		} else {
			DateTime dateTime = new DateTime().withMillisOfDay(0);
			startTime = dateTime.toDate();
			Calendar c = Calendar.getInstance();
            c.setTime(startTime);
            c.add(Calendar.HOUR, (hour - 2));
            startTime = c.getTime();
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.setTime(startTime);
        c.add(Calendar.HOUR, 1);
        endTime = c.getTime();
        System.out.println(sdf1.format(startTime)+sdf1.format(endTime));
        //设备登记表
        List<DeviceLog> deviceLogs = deviceLogMapper.selectByTimeStampDateGroupByDeviceId(Timestamp.valueOf(sdf1.format(startTime)), Timestamp.valueOf(sdf1.format(endTime)));
        deviceService.createDeviceData(deviceLogs);
        
        //用户画像表
        List<User> users =  userMapper.timeStampCreatedAtBetween(Timestamp.valueOf(sdf1.format(startTime)), Timestamp.valueOf(sdf1.format(endTime)));
        System.out.println(users.size());
        
        UserExample userExample = new UserExample();
        userExample.createCriteria().andCreatedAtBetween(startTime, endTime);
        List<User> users1 = userMapper.selectByExample(userExample);
        System.out.println("新增用户：" + users1.size());
        
        for (User user : users) {
        	userService.createUserPortrayalByUser(user);
        }
        
        //日新增登陆统计表     
        DeviceLogExample deviceLogExample = new DeviceLogExample();
        Criteria criteria = deviceLogExample.createCriteria();
        criteria.andUriLike("%" + "panda_loan/tokens" + "%");
        criteria.andCreatedAtBetween(startTime, endTime);
        deviceLogs = deviceLogMapper.selectByExample(deviceLogExample);
        System.out.println("5:" + deviceLogs.size());
		for (DeviceLog deviceLog : deviceLogs) {
			logInService.createLogInByDeviceLog(deviceLog);
		}
		
		System.out.println("结束了");
	}
	
}
