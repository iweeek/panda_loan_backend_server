package com.pinganzhiyuan.timer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.DeviceLogExample.Criteria;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;
import com.pinganzhiyuan.service.DateTransformService;
import com.pinganzhiyuan.service.DeviceService;
import com.pinganzhiyuan.service.LogInService;
import com.pinganzhiyuan.service.UserService;

@Component
public class HourlyStatisticTask {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogInService logInService;
	
	@Autowired
	private DateTransformService dateTransformService;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ClientMapper clientMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Scheduled(cron = "0 56 10 * * ?")
	public void statisticDevice() {
		System.out.println("开始了");
		DateTime yesterday = new DateTime().withMillisOfDay(0).minusDays(1);
        Date startDate = yesterday.toDate();
        DateTime now = new DateTime().withMillisOfDay(0);
        Date endDate = now.toDate();
        System.out.println(sdf.format(startDate) + " " + sdf.format(endDate));
        //设备登记表
        System.out.println(1);
        List<DeviceLog> deviceLogs = deviceLogMapper.selectByDateGroupByDeviceId(startDate, endDate);
        deviceService.createDeviceData(deviceLogs);
        //日新增设备统计数据
        System.out.println(2);
        deviceService.deviceStatisticDaily(startDate, endDate);
        //日新增设备的时间分布
        System.out.println(3);
    	Date stageStartDate = startDate;
        Date stageEndDate = new Date();
        for (int i = 0;i < 12;i ++) {
        	Calendar c = Calendar.getInstance();
            c.setTime(stageStartDate);
            c.add(Calendar.HOUR, 2);// +2小时 
            stageEndDate = c.getTime();
            System.out.println(sdf.format(stageEndDate) + sdf.format(stageStartDate));
            deviceService.deviceStatisticTime(
            		Timestamp.valueOf(sdf.format(stageStartDate)),Timestamp.valueOf(sdf.format(stageEndDate)));
            stageStartDate = stageEndDate;
        }
        
        //用户画像表
        System.out.println(4);
        //1.新增认证
        ClientExample clientExample = new ClientExample();
        clientExample.createCriteria().andCreatedAtBetween(startDate, endDate);
        List<Client> clients = clientMapper.selectByExample(clientExample);
        System.out.println("huaxiang:" + clients.size());
        userService.updateUserPortrayal(clients);
        //2.新增用户
        UserExample userExample = new UserExample();
        userExample.createCriteria().andCreatedAtBetween(startDate, endDate);
        List<User> users =  userMapper.selectByExample(userExample);
        System.out.println("新增用户：" + users.size());
        for (User user : users) {
        	userService.createUserPortrayalByUser(user);
        }
        
        //日新增登录统计数据
        System.out.println(5);
        DeviceLogExample deviceLogExample = new DeviceLogExample();
        Criteria criteria = deviceLogExample.createCriteria();
        criteria.andUriLike("%" + "panda_loan/tokens" + "%");
        criteria.andCreatedAtBetween(startDate, endDate);
        deviceLogs = deviceLogMapper.selectByExample(deviceLogExample);
        System.out.println("5:" + deviceLogs.size());
		for (DeviceLog deviceLog : deviceLogs) {
			logInService.createLogInByDeviceLog(deviceLog);
		}
        
		//流量转化记录
		System.out.println(6);
		//sid不为null的情况
		deviceLogs = deviceLogMapper.selectByUserIdAndSidAndDate(startDate, endDate);
		System.out.println("流量1：" + deviceLogs.size());
		dateTransformService.createDateTransformByDeviceLog(deviceLogs);
		//sid等于null的情况
		deviceLogs = deviceLogMapper.selectByUserIdAndIsWebview(startDate, endDate);
		System.out.println("流量2：" + deviceLogs.size());//64
		dateTransformService.createDateTransformByDeviceLogNoSid(deviceLogs);
		
		System.out.println("结束了");
		
	}
}
