package com.pinganzhiyuan.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.mapper.StatisticDateTransformMapper;
import com.pinganzhiyuan.model.DeviceExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.DeviceLogExample.Criteria;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.StatisticDateTransform;
import com.pinganzhiyuan.model.StatisticDateTransformExample;
import com.pinganzhiyuan.service.DateTransformService;
@Service
public class DateTransformServiceImpl implements DateTransformService {

	@Autowired
    private DeviceLogMapper deviceLogMapper;

	@Autowired
    private DeviceMapper deviceMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private StatisticDateTransformMapper statisticDateTransformMapper;
	
	@Override
	public void createDateTransformByDeviceLog(List<DeviceLog> deviceLogs) {
		// TODO Auto-generated method stub
		for (DeviceLog deviceLog : deviceLogs) {
			DeviceLogExample example = new DeviceLogExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(deviceLog.getUserId());
			criteria.andSidEqualTo(deviceLog.getSid());
			List<DeviceLog> onceDeviceLogs = deviceLogMapper.selectByExample(example);
			int stayTime = (int) ((onceDeviceLogs.get(onceDeviceLogs.size() - 1).getCreatedAt().getTime()
					- onceDeviceLogs.get(0).getCreatedAt().getTime()) / 1000);
			if (stayTime != 0) {
				StatisticDateTransform statisticDateTransform = new StatisticDateTransform();
				DeviceExample deviceExample = new DeviceExample();
				deviceExample.createCriteria().andDeviceIdEqualTo(deviceLog.getDeviceId());
				if (deviceMapper.selectByExample(deviceExample).size() != 0) {
					statisticDateTransform.setDeviceId(deviceMapper.selectByExample(deviceExample).get(0).getId());
				} else {
					statisticDateTransform.setDeviceId(0L);
				}
				statisticDateTransform.setUserId(deviceLog.getUserId());
				statisticDateTransform.setSid(deviceLog.getSid());
				statisticDateTransform.setProductId(deviceLog.getpId());
				Product product = productMapper.selectByPrimaryKey(deviceLog.getpId());
				if (product != null) {
					statisticDateTransform.setProductName(product.getTitle());
					statisticDateTransform.setProductUrl(product.getUrl());
				} else {
					statisticDateTransform.setProductName("没找到这个产品");
					statisticDateTransform.setProductUrl("没找到这个产品");
				}
				statisticDateTransform.setStartVisitTime(onceDeviceLogs.get(0).getCreatedAt());
				statisticDateTransform.setEndVisitTime(onceDeviceLogs.get(onceDeviceLogs.size() - 1).getCreatedAt());
				statisticDateTransform.setStayTime(stayTime);
				//这些字段还没有
				statisticDateTransform.setIsRequestCode(false);
				statisticDateTransform.setIsRequestRegister(false);
				
				statisticDateTransform.setCreatedAt(new Date());
				//根据用户和访问开始时间判断这条数据是否存在，若存在更新，不存在插入
				StatisticDateTransformExample statisticDateTransformExample = new StatisticDateTransformExample();
				com.pinganzhiyuan.model.StatisticDateTransformExample.Criteria scriterid = statisticDateTransformExample.createCriteria();
				scriterid.andUserIdEqualTo(statisticDateTransform.getUserId());
				scriterid.andStartVisitTimeEqualTo(statisticDateTransform.getStartVisitTime());
				if (statisticDateTransformMapper.selectByExample(statisticDateTransformExample).size() != 0) {
					statisticDateTransform.setId(statisticDateTransformMapper
							.selectByExample(statisticDateTransformExample)
							.get(0).getId());
					statisticDateTransformMapper.updateByPrimaryKey(statisticDateTransform);
				} else {
					statisticDateTransformMapper.insert(statisticDateTransform);
				}
			}
			
		}
	}

	@Override
	public void createDateTransformByDeviceLogNoSid(List<DeviceLog> deviceLogs) {
		// TODO Auto-generated method stub
			List<DeviceLog> amountDeviceLogs = new ArrayList();
			for (int i = 0;i < deviceLogs.size();i++) {
				if (i < (deviceLogs.size() - 1)){
					DeviceLogExample example = new DeviceLogExample();
					Criteria criteria = example.createCriteria();
					criteria.andUserIdEqualTo(deviceLogs.get(i).getUserId());
					criteria.andPIdEqualTo(deviceLogs.get(i).getpId());
					criteria.andIsWebviewIsNotNull();
					criteria.andCreatedAtBetween(deviceLogs.get(i).getCreatedAt(), deviceLogs.get(i+1).getCreatedAt());
					amountDeviceLogs = deviceLogMapper.selectByExample(example);
				} else if (i == (deviceLogs.size() - 1)){
					System.out.println("最后一个" + i);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<DeviceLog> twoDeviceLogs = deviceLogMapper.selectByDate(Timestamp.valueOf(sdf.format(deviceLogs.get(i).getCreatedAt())));
					
					System.out.println(sdf.format(deviceLogs.get(i).getCreatedAt()));
					System.out.println("最后一个尺寸应该为2：" + twoDeviceLogs.size());
					
					DeviceLogExample example = new DeviceLogExample();
					Criteria criteria = example.createCriteria();
					criteria.andUserIdEqualTo(deviceLogs.get(i).getUserId());
					criteria.andPIdEqualTo(deviceLogs.get(i).getpId());
					criteria.andIsWebviewIsNotNull();
					System.out.println(sdf.format(twoDeviceLogs.get(0).getCreatedAt()) + " " + sdf.format(twoDeviceLogs.get(1).getCreatedAt()));
					criteria.andCreatedAtBetween(twoDeviceLogs.get(0).getCreatedAt(), twoDeviceLogs.get(1).getCreatedAt());
					amountDeviceLogs = deviceLogMapper.selectByExample(example);
					System.out.println(amountDeviceLogs.size());
				}
		
	        int stayTime = (int) ((amountDeviceLogs.get(amountDeviceLogs.size() - 1).getCreatedAt().getTime()
					- amountDeviceLogs.get(0).getCreatedAt().getTime()) / 1000);
			
	        if (stayTime != 0) {
				StatisticDateTransform statisticDateTransform = new StatisticDateTransform();
				DeviceExample deviceExample = new DeviceExample();
				deviceExample.createCriteria().andDeviceIdEqualTo(deviceLogs.get(i).getDeviceId());
				if (deviceMapper.selectByExample(deviceExample).size() != 0) {
					statisticDateTransform.setDeviceId(deviceMapper.selectByExample(deviceExample).get(0).getId());
				} else {
					statisticDateTransform.setDeviceId(0L);
				}
				statisticDateTransform.setUserId(deviceLogs.get(i).getUserId());
				statisticDateTransform.setSid("暂无");
				statisticDateTransform.setProductId(deviceLogs.get(i).getpId());
				Product product = productMapper.selectByPrimaryKey(deviceLogs.get(i).getpId());
				if (product != null) {
					statisticDateTransform.setProductName(product.getTitle());
					statisticDateTransform.setProductUrl(product.getUrl());
				} else {
					statisticDateTransform.setProductName("没找到这个产品");
					statisticDateTransform.setProductUrl("没找到这个产品");
				}
				statisticDateTransform.setStartVisitTime(amountDeviceLogs.get(0).getCreatedAt());
				statisticDateTransform.setEndVisitTime(amountDeviceLogs.get(amountDeviceLogs.size() -1).getCreatedAt());
				statisticDateTransform.setStayTime(stayTime);
				//这些字段还没有
				statisticDateTransform.setIsRequestCode(false);
				statisticDateTransform.setIsRequestRegister(false);
				
				statisticDateTransform.setCreatedAt(new Date());
				//根据用户和访问开始时间判断这条数据是否存在，若存在更新，不存在插入
				StatisticDateTransformExample statisticDateTransformExample = new StatisticDateTransformExample();
				com.pinganzhiyuan.model.StatisticDateTransformExample.Criteria scriterid = statisticDateTransformExample.createCriteria();
				scriterid.andUserIdEqualTo(statisticDateTransform.getUserId());
				scriterid.andStartVisitTimeEqualTo(statisticDateTransform.getStartVisitTime());
				if (statisticDateTransformMapper.selectByExample(statisticDateTransformExample).size() != 0) {
					statisticDateTransform.setId(statisticDateTransformMapper
							.selectByExample(statisticDateTransformExample)
							.get(0).getId());
					statisticDateTransformMapper.updateByPrimaryKey(statisticDateTransform);
				} else {
					statisticDateTransformMapper.insert(statisticDateTransform);
				}
			}	
	        
		}
	}

}
