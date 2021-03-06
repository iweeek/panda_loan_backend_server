package com.pinganzhiyuan.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.mapper.ColumnMapper;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.service.ChannelService;
import com.pinganzhiyuan.service.ColumnService;
import com.pinganzhiyuan.util.ResponseBody;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
    
    @Override
    public int index(List<Channel> pointList, ResponseBody resBody) {
        List<Channel> list = channelMapper.selectByExample(null);
        
        if (list != null && list.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = list;
            return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_NOT_FOUND;
    }

	@Override
	public int indexChannelByAppName(String appName, ResponseBody resBody) {
		List<Channel> list = channelMapper.selectByAppName(appName);
		
		if (list != null && list.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = list;
            return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_NOT_FOUND;
	}

}
