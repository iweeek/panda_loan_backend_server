package com.pinganzhiyuan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.StandardStatementStatisticsMapper;
import com.pinganzhiyuan.model.StandardStatementStatistics;
import com.pinganzhiyuan.service.StandardStatementService;
@Service
public class StandardStatementServiceImpl implements StandardStatementService {

	@Autowired
	private StandardStatementStatisticsMapper standardStatementaMapper;
	
	@Override
	public void createStandardStatement(Date date, String appProductName, String packageName, Long channelId,
			int version) {
		// TODO Auto-generated method stub
		//111111
		StandardStatementStatistics standardStatementStatistics = new StandardStatementStatistics();
		standardStatementStatistics.setNewDeviceCount(
				standardStatementaMapper
				.selectNewDeviceCountByAppProductNameAndDate(appProductName,date));
		System.out.println(standardStatementStatistics.getNewDeviceCount());
		
		/////22222
		List<Integer> newUserCounts = standardStatementaMapper
		.selectNewUserCountByPackageNameDateChannelIdAndVersion(packageName,date,channelId,version);
		
		if (newUserCounts.size() > 1) {
			standardStatementStatistics.setNewUserCount(newUserCounts.get(0) + newUserCounts.get(1));
		} else {
			standardStatementStatistics.setNewUserCount(newUserCounts.get(0));
		}
		System.out.println(standardStatementStatistics.getNewUserCount());
		
		//////3333333
		standardStatementStatistics.setNewUserProductUvCount(
				standardStatementaMapper
				.selectNewUserProductUvCountByPackageNameDateChannelIdAndVersion(packageName,date,channelId,version)
				);
		System.out.println(standardStatementStatistics.getNewUserProductUvCount());
		
		
		/////4444444
		List<Integer> activeDeviceCounts = standardStatementaMapper
				.selectActiveDeviceCountByPackageNameDateChannelIdAndVersion(packageName,date,channelId,version);
		if (activeDeviceCounts.size() > 1) {
			standardStatementStatistics.setActiveDeviceCount(activeDeviceCounts.get(0)
					+ activeDeviceCounts.get(1));
		} else {
			standardStatementStatistics.setActiveDeviceCount(activeDeviceCounts.get(0));
		}
		
		System.out.println(standardStatementStatistics.getActiveDeviceCount());
		
		//////5555555
		List<Integer> activeUserCounts = standardStatementaMapper
				.selectActiveUserCountByPackageNameDateChannelIdAndVersion(packageName,date,channelId,version);
		if (activeUserCounts.size() > 1) {
			standardStatementStatistics.setActiveUserCount(activeUserCounts.get(0) + activeUserCounts.get(1));
		} else {
			standardStatementStatistics.setActiveUserCount(activeUserCounts.get(0));
		}
		System.out.println(standardStatementStatistics.getActiveUserCount());
		
		
		
		/////66666
		standardStatementStatistics.setAllProductUvCount(
				standardStatementaMapper
				.selectAllProductUvCountByPackageNameDateChannelIdAndVersion(packageName,date,channelId,version)
				);
		System.out.println(standardStatementStatistics.getAllProductUvCount());
		standardStatementStatistics.setAppProductName(appProductName);
		standardStatementStatistics.setStatisticDate(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
		standardStatementaMapper.insert(standardStatementStatistics);
		
	}

	@Override
	public void createNewStandardStatement(Date date, String appProductName, Long channelId, Integer versionCode) {
		// TODO Auto-generated method stub
		StandardStatementStatistics standardStatementStatistics = new StandardStatementStatistics();
		standardStatementStatistics.setNewDeviceCount(
				standardStatementaMapper.selectNewDeviceCountByAppProductNameAndChannelIdAndDate(date,appProductName,channelId));
		}
	
	//第二个新增用户要根据安卓，ios区分
	
	
	
}
