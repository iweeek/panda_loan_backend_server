package com.pinganzhiyuan.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.AppClientMapper;
import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.mapper.ColumnMapper;
import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.AppClientExample;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.service.AppClientService;
import com.pinganzhiyuan.service.ChannelService;
import com.pinganzhiyuan.service.ColumnService;
import com.pinganzhiyuan.util.ResponseBody;

@Service
public class AppClientServiceImpl implements AppClientService {

    @Autowired
    private AppClientMapper appClientMapper;

    @Override
    public int index(List<AppClient> pointList, ResponseBody resBody) {
        List<AppClient> list = appClientMapper.selectAllWithChannel();
        AppClientExample example = new AppClientExample();
      
        if (list != null && list.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = list;
            return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_NOT_FOUND;
    }

}
