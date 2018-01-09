package com.pinganzhiyuan.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.pinganzhiyuan.model.DeviceLog;

public interface DeviceService {
	//生成设备信息
	public void createDeviceData(List<DeviceLog> deviceLogs);

	public void deviceStatisticDaily(Date startDate, Date endDate);

	public void deviceStatisticTime(Timestamp startDate, Timestamp endDate);


}
