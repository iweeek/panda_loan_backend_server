package com.pinganzhiyuan.service;

import com.pinganzhiyuan.model.ClientVersion;

public interface ClientVersionService {

	ClientVersion getLatestVersionInfo(byte platformId, long channelId, String packageName);

    ClientVersion getVersion(byte platformId, long channelId, String packageName, Integer versionCode);

}
