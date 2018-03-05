package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.util.ResponseBody;

public interface AppClientService {
    
    int index(List<AppClient> pointList, ResponseBody resBody);

    /**
     * 根据渠道获取到 app 集合
     * @param channelId
     * @param resBody
     * @return
     */
	int indexAppClientByChannelId(String channelId, ResponseBody resBody);
    
}
