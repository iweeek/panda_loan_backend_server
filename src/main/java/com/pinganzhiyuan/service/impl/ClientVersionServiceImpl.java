package com.pinganzhiyuan.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.service.ClientVersionService;
import com.pinganzhiyuan.util.ResponseBody;

@Service
public class ClientVersionServiceImpl implements com.pinganzhiyuan.service.ClientVersionService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientVersionServiceImpl.class);
	
	String logMsg = "";

	@Autowired
	private ClientVersionMapper clientVersionMapper;

    @Override
    public ClientVersion getLatestVersionInfo(byte platformId, long channelId, String packageName) {
        ClientVersionExample example = new ClientVersionExample();
        example.createCriteria().andPackageNameEqualTo(packageName).andPlatformIdEqualTo(platformId).andChannelIdEqualTo(channelId).andIsPublishedEqualTo(true);
        example.setOrderByClause("publish_time desc");
        
        List<ClientVersion> list = clientVersionMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public ClientVersion getVersion(byte platformId, long channelId, String packageName, Integer versionCode) {
        ClientVersionExample example = new ClientVersionExample();
        example.createCriteria().andPackageNameEqualTo(packageName).andPlatformIdEqualTo(platformId).andChannelIdEqualTo(channelId).andIsPublishedEqualTo(true)
        .andVersionCodeEqualTo(versionCode);
        example.setOrderByClause("publish_time desc");
        
        List<ClientVersion> list = clientVersionMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
	

}
