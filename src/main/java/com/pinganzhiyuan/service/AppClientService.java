package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.util.ResponseBody;

public interface AppClientService {
    
    int index(List<AppClient> pointList, ResponseBody resBody);
    
}
