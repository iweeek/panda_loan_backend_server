package com.pinganzhiyuan.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ColumnMapper;
import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.service.ColumnService;
import com.pinganzhiyuan.util.ResponseBody;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;
    
    @Override
    public int index(List<Column> pointList, ResponseBody resBody) {
        List<Column> list = columnMapper.selectByExample(null);
        
        if (list != null && list.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = list;
            return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_NOT_FOUND;
    }

}
