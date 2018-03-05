package com.pinganzhiyuan.service;


import java.util.List;

import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.util.ResponseBody;

public interface ClientVersionService {

	ClientVersion getLatestVersionInfo(byte platformId, long channelId, String packageName);

    ClientVersion getVersion(byte platformId, long channelId, String packageName, Integer versionCode);
    
    List<ClientVersion> screen();

	int create(ClientVersion clientVersion, ResponseBody resBody);

	int update(ClientVersion clientVersion, ResponseBody resBody);

	/**
	 * 根据包名去查找appName
	 * @param packageName
	 * @param resBody
	 * @return
	 */
	int getAppNameByPackageName(String packageName, ResponseBody resBody);

	int selectByAppName(String appName, ResponseBody resBody);
}
