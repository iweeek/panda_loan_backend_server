package com.pinganzhiyuan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.ProductStatisticMapper;
import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.ProductStatistic;
import com.pinganzhiyuan.model.Statistic;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.service.StatisticTaskService;

/**
 * 埋点统计任务
 * @author SmartNi
 */
@Service
public class StatisticTaskServiceImpl implements StatisticTaskService {

    @Autowired
    private DeviceLogMapper deviceLogMapper;
    
    @Autowired
    private DeviceMapper deviceMapper;
    
    @Autowired
    private UserMapper usesrMapper;
    
    @Autowired
    StatisticMapper statisticMapper;

    @Autowired
    private ProductStatisticMapper productStatisticMapper;
    
    private static final Logger logger = LogManager.getLogger(StatisticTaskService.class);


    @Override
    public int newDeviceCount(Date startDate, Date endDate) {
//   example.createCriteria().andCreatedAtBetween(startDate, endDate);
        
        List<DeviceLog> yesterdayList = deviceLogMapper.getDeviceLogBetweenDataList(startDate, endDate);
//        List<DeviceLog> allList = deviceLogMapper.getAllGroupByDeviceDataList(startDate);
        List<Device> exisitList = deviceMapper.getExisitDeviceDataList(startDate, endDate);
        List<DeviceLog> temp = new ArrayList<>(yesterdayList);
        
        for (Device d : exisitList) {
            for (DeviceLog device : yesterdayList) {
                if (d.getDeviceId().equals(device.getDeviceId())) {
                    temp.remove(device);
                }
            }
        }
        
        Device device = null;
        for (DeviceLog log : temp) {
            device = new Device();
            device.setDeviceId(log.getDeviceId());
            device.setUserId(log.getUserId());
            device.setUserAgent(log.getUserAgent());
            device.setVersion(log.getVersion());
            device.setChannelId(log.getChannelId());
            device.setRecordDate(new DateTime(log.getCreatedAt()).toDate());
            deviceMapper.insert(device);
        }
        return temp.size();
    }
    // 访问设备数
    @Override
    public int deviceVisitCount(Date startDate, Date endDate) {
        return deviceLogMapper.getDeviceLogBetweenDataList(startDate, endDate).size();
    }

    // 访问用户数
    @Override
    public int userVisitCount(Date startDate, Date endDate) {
        return deviceLogMapper.getGroupByUserDataList(startDate, endDate).size();
    }
    //新增用户数
    @Override
    public int newUserCount(Date startDate, Date endDate) {
        List<DeviceLog> yesterdayList = deviceLogMapper.getGroupByUserDataList(startDate, endDate);
//        List<DeviceLog> allList = deviceLogMapper.getAllGroupByUserDataList(startDate);
        List<User> exisitList = usesrMapper.getExisitUserDataList(startDate, endDate);
        List<DeviceLog> temp = new ArrayList<>(yesterdayList);
        
        for (User u : exisitList) {
            for (DeviceLog device : yesterdayList) {
                if (u.getId().equals(device.getUserId())) {
                    temp.remove(device);
                }
            }
        }
        return temp.size();
    }

    // 产品访问总次数
    @Override
    public int productVisitTotalCount(Date startDate, Date endDate) {
        DeviceLogExample example = new DeviceLogExample();
        example.createCriteria().andCreatedAtBetween(startDate, endDate).andPIdIsNotNull();
        List<DeviceLog> list = deviceLogMapper.selectByExample(example);
        return list.size();
    }
    // 产品访问总人数
    @Override
    public int productVisitUserTotalCount(Date startDate, Date endDate) {
        return deviceLogMapper.getAllProductVisitUserDataList(startDate, endDate).size();
    }

    // 平均每人浏览产品数
    @Override
    public double averageProductVisitCount(Date startDate, Date endDate) {
        int userCount = deviceLogMapper.getAllProductVisitUserDataList(startDate, endDate).size();
        // 所有人浏览的产品数
        List<DeviceLog> visitCount = deviceLogMapper.getAverageUserVisitProductDataList(startDate, endDate);
        if (userCount == 0) {
            return 0;
        }
        BigDecimal b = new BigDecimal(visitCount.size()).divide(new BigDecimal(userCount), 1, RoundingMode.HALF_DOWN);
        return b.doubleValue();
    }
    
    @Override
    public void statisticTask(Date startDate, Date endDate) {
        int newDeviceCount = newDeviceCount(startDate, endDate);
        int newUserCount = newUserCount(startDate, endDate);
        int deviceVisitCount = deviceVisitCount(startDate, endDate);
        int userVisitCount = userVisitCount(startDate, endDate);
        int productVisitUserTotalCount = productVisitUserTotalCount(startDate, endDate);
        int productVisitTotalCount = productVisitTotalCount(startDate, endDate);
        double averageProductVisitCount = averageProductVisitCount(startDate, endDate);
        
        Statistic statistic = new Statistic();
        statistic.setNewDeviceCount(newDeviceCount);
        statistic.setNewUserCount(newUserCount);
        statistic.setDeviceVisitCount(deviceVisitCount);
        statistic.setUserVisitCount(userVisitCount);
        statistic.setProductVisitUserTotalCount(productVisitUserTotalCount);
        statistic.setProductVisitTotalCount(productVisitTotalCount);
        statistic.setAverageProductVisitCount(averageProductVisitCount);
        statistic.setRecordDate(new DateTime(startDate).toDate());
        
        statisticMapper.insert(statistic);
    }
    
    // 每个产品的访问次数
    @Override
    public void productStatisticTask(Date startDate, Date endDate) {
        List<ProductStatistic> list = productStatisticMapper.getProductStatisticDataList(startDate, endDate);
        for (ProductStatistic ps : list) {
            ps.setAverageStayTime(0);
            productStatisticMapper.insertSelective(ps);
        }
    }
    // 每个产品的访问次数
    @Override
    public int visitUserCountByProduct(Date startDate, Date endDate) {
        return 0;
    }
    @Override
    public int averageStayTime(Date startDate, Date endDate) {
        return 0;
    }
    @Override
    public int visitCountByProduct(Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        return 0;
    }
   
}
