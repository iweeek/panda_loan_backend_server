package com.pinganzhiyuan.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.DeviceStatisticDailyMapper;
import com.pinganzhiyuan.mapper.DeviceStatisticTimeMapper;
import com.pinganzhiyuan.model.ChannelExample;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.model.ClientVersionExample.Criteria;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.DeviceExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.DeviceStatisticDaily;
import com.pinganzhiyuan.model.DeviceStatisticTime;
import com.pinganzhiyuan.model.DeviceStatisticalData;
import com.pinganzhiyuan.service.DeviceService;
@Service
public class DeviceServiceImpl implements DeviceService {
	
    final String IPHONE = "iPhone";
	final String ANDROID = "Android";
	final String IPAD = "iPad";
	@Autowired
    private DeviceMapper deviceMapper;
	
	@Autowired
    private DeviceLogMapper deviceLogMapper;
	
	@Autowired
	private ClientVersionMapper clientVersionMapper;
	
	@Autowired
	private ChannelMapper channelMapper;

	@Autowired
	private DeviceStatisticDailyMapper deviceStatisticDailyMapper;
	
	@Autowired
	private DeviceStatisticTimeMapper deviceStatisticTimeMapper;
	@Override
	public void createDeviceData(List<DeviceLog> deviceLogs) {
		// TODO Auto-generated method stub
			
		for (DeviceLog deviceLog : deviceLogs) {
			//System.out.println("开始处理id为："+deviceLog.getId()+"的信息");
			DeviceExample deviceExample = new DeviceExample();
			deviceExample.createCriteria().andDeviceIdEqualTo(deviceLog.getDeviceId());
			//根据device_id设备生成码判断是否已存在数据
			if (deviceMapper.selectByExample(deviceExample).size() == 0) {
				Device device = new Device();
				device.setDeviceId(deviceLog.getDeviceId());
				//设备品牌
				if (deviceLog.getUserAgent().indexOf(IPHONE) != -1) {//苹果
					device.setDeviceBrand(IPHONE);
					//PandaLoan/1.0 (iPhone; iOS 11.2; Scale/2.00)
					//Mozilla/5.0 (iPhone; CPU iPhone OS 11_2 like Mac OS X) AppleWebKit/604.4.7 (KHTML, like Gecko) Mobile/15C10
					Pattern p = Pattern.compile("\\; (.*?)\\; ");//正则表达式，取; 和; 之间的字符串  
					Matcher m = p.matcher(deviceLog.getUserAgent());  
					Pattern p1 = Pattern.compile("iPhone; (.*?) Apple");//正则表达式，取; 和; 之间的字符串  
					Matcher m1 = p1.matcher(deviceLog.getUserAgent()); 
					if (m.find()) {
						device.setDeviceSystemVersion(m.group(1));//m.group(0)包括这两个字符
					} else if (m1.find()) {
						device.setDeviceSystemVersion(m1.group(1).substring(0, m1.group(1).length()-1));
					} else {
						device.setDeviceSystemVersion("未找到iphone系统版本");
					}
				} else if (deviceLog.getUserAgent().indexOf(IPAD) != -1){
					//PandaLoan/1.0 (iPad; iOS 11.2.1; Scale/2.00)
					device.setDeviceBrand(IPAD);
					Pattern p = Pattern.compile("iPad; (.*?); ");//正则表达式，取; 和; 之间的字符串  
					Matcher m = p.matcher(deviceLog.getUserAgent());  
					if (m.find()) {
						device.setDeviceSystemVersion(m.group(1));//m.group(0)包括这两个字符
					} else {
						device.setDeviceSystemVersion("未找到iPad系统版本");
					}
				} else if (deviceLog.getUserAgent().indexOf(ANDROID) != -1){
					//系统版本
					//(Linux; Android 7.0; BLN-AL10 Build/HONORBLN-AL10; wv)       p3
					//(Linux; U; Android 5.1; zh-cn; 1501_M02 Build/LMY47D)		   p2
					//(Linux; U; Android 7.1.1; zh-hk; SM-C9000 Build/NMF26X)      p2
					//(Linux; U; Android 5.1; en-us; GI-I9500_TMMARS Build/LMY47D) p2
					//(Linux; U; Android 6.0; zh-CN; lephone W7+ Build/MRA58K)     p2
					//System.out.println(deviceLog.getUserAgent());
				    Pattern p=Pattern.compile("Linux; U; (.*?); ");
				    Matcher m=p.matcher(deviceLog.getUserAgent());
				    Pattern p1=Pattern.compile("Linux; (.*?); ");
				    Matcher m1=p1.matcher(deviceLog.getUserAgent());
				    if(m.find()){
				        device.setDeviceSystemVersion(m.group(1)); 
				    } else if (m1.find()) {
				        device.setDeviceSystemVersion(m1.group(1)); 
				    } else {
				    	device.setDeviceSystemVersion(deviceLog.getUserAgent());
				    }
				    //手机型号
				    Pattern p2=Pattern.compile("Linux; U; (.*?)d/");
				    Matcher m2=p2.matcher(deviceLog.getUserAgent());
				    Pattern p3=Pattern.compile("; (.*?)ld/");
				    Matcher m3=p3.matcher(deviceLog.getUserAgent());
				    if(m2.find()){
				    	Pattern p5=Pattern.compile("; (.*?)il");
					    Matcher m5=p5.matcher(m2.group(1));
				    	if (m5.find()) {
					    	Pattern p6=Pattern.compile("; (.*?) Bu");
						    Matcher m6=p6.matcher(m5.group(1));
				    		if (m6.find()) {
						    	device.setDeviceBrand(m6.group(1));
				    		}
				    	} else {
					    	device.setDeviceBrand(m2.group(1));
				    	}
				    } else if (m3.find()) {
					    Pattern p4=Pattern.compile("; (.*?) Bui");
					    Matcher m4=p4.matcher(m3.group(1));
				    	if (m4.find()) {
					    	device.setDeviceBrand(m4.group(1));
				    	}
				    } else {
				    	device.setDeviceBrand(deviceLog.getUserAgent());
				    }
				} else {
					device.setDeviceBrand("未找到手机型号");
					device.setDeviceSystemVersion("未找到系统版本");
				}
				//下载渠道
				device.setChannelId(deviceLog.getChannelId());
				device.setChannelName(
					channelMapper.selectByPrimaryKey(deviceLog.getChannelId()).getName()
				);
				//首次启动信息
				device.setMaidenStartTime(deviceLog.getCreatedAt());
				device.setMaidenStartIp(deviceLog.getIp());
				device.setMaidenStartLatitude(deviceLog.getLatitude());
				device.setMaidenStartLongitude(deviceLog.getLongitude());
				//是否越狱或ROOT
				device.setIsRoot(false);
				//APP产品信息
				ClientVersionExample clientVersionExample = new ClientVersionExample();
				Criteria criteria = clientVersionExample.createCriteria();
				criteria.andChannelIdEqualTo(deviceLog.getChannelId());
				if (deviceLog.getPackageName() == null) {
					System.out.println("空的");
				}
				if (deviceLog.getPackageName() != null) {
					criteria.andPackageNameEqualTo(deviceLog.getPackageName());//有包名根据包名和渠道id取
				}
				
				ClientVersionExample clientVersionExampleNoPackageName = new ClientVersionExample();
				Criteria criteriaNoPackageName = clientVersionExampleNoPackageName.createCriteria();
				criteriaNoPackageName.andChannelIdEqualTo(deviceLog.getChannelId());
				criteriaNoPackageName.andVersionCodeEqualTo(deviceLog.getVersion());//无包名根据version_code和渠道id取
							
				if (clientVersionMapper.selectByExample(clientVersionExample).size() != 0 && deviceLog.getPackageName() != null) {
					ClientVersion clientVersion = clientVersionMapper.selectByExample(clientVersionExample).get(0);
					device.setAppProductId(clientVersion.getId());
					device.setAppProductName(clientVersion.getName());
					device.setAppProductVersion(clientVersion.getVersionName());
				} else if (clientVersionMapper.selectByExample(clientVersionExampleNoPackageName).size() != 0) {
					ClientVersion clientVersion = clientVersionMapper.selectByExample(clientVersionExampleNoPackageName).get(0);
					device.setAppProductId(clientVersion.getId());
					device.setAppProductName(clientVersion.getName());
					device.setAppProductVersion(clientVersion.getVersionName());
				} else {
					device.setAppProductId(0L);
					device.setAppProductName("无");
					device.setAppProductVersion("无");
				}
				
				//插入数据表
				device.setCreatedAt(new Date());
				deviceMapper.insert(device);
			}
		}
	}

	@Override
	public void deviceStatisticDaily(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<DeviceStatisticalData> deviceStatisticalDatas = deviceMapper.getDeviceStatisticalDataList(startDate,endDate);
		if (deviceStatisticalDatas.size() != 0) {
			for (DeviceStatisticalData deviceStatisticalData : deviceStatisticalDatas) {
				DeviceStatisticDaily deviceStatisticDaily = new DeviceStatisticDaily();
				deviceStatisticDaily.setAppProductId(deviceStatisticalData.getAppProductId());
				deviceStatisticDaily.setActivateDeviceCount(deviceStatisticalData.getCountNumber());
				deviceStatisticDaily.setAppProductName(deviceStatisticalData.getAppProductName());
				deviceStatisticDaily.setChannelId(deviceStatisticalData.getChannelId());
				deviceStatisticDaily.setChannelName(deviceStatisticalData.getChannelName());
				deviceStatisticDaily.setStatisticDate(startDate);
				deviceStatisticDaily.setCreatedAt(new Date());
				deviceStatisticDailyMapper.insert(deviceStatisticDaily);
			}
		}
	}

	@Override
	public void deviceStatisticTime(Timestamp startDate, Timestamp endDate) {
		// TODO Auto-generated method stub
		int countNumber = deviceMapper.getDeviceStatisticTimeCount(startDate,endDate);
		if (countNumber != 0) {
			DeviceStatisticTime deviceStatisticTime = new DeviceStatisticTime();
			deviceStatisticTime.setStatisticDate(startDate);
			deviceStatisticTime.setActivateDeviceCount(countNumber);
			deviceStatisticTime.setStatisticTime(startDate);
			deviceStatisticTime.setCreatedAt(new Date());
			deviceStatisticTimeMapper.insert(deviceStatisticTime);
		}
	}
}
