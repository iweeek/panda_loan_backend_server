package com.pinganzhiyuan.service;

import java.util.Date;

public interface StandardStatementService {

	public void createStandardStatement(Date date, String appProductName, String packageName, Long channelId, int version);

	public void createNewStandardStatement(Date date, String appProductName, Long channelId, Integer versionCode);

}
