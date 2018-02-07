package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.util.ResponseBody;

public interface ChannelService {
    
    int index(List<Channel> pointList, ResponseBody resBody);
    
}
