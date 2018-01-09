package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.DeviceLog;

public interface DateTransformService {

	public void createDateTransformByDeviceLog(List<DeviceLog> deviceLogs);

	public void createDateTransformByDeviceLogNoSid(List<DeviceLog> deviceLogs);

}
