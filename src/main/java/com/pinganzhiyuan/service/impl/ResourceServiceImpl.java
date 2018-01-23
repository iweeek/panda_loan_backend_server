package com.pinganzhiyuan.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ResourceMapper;
import com.pinganzhiyuan.model.Resource;
import com.pinganzhiyuan.service.ResourceService;


/**
* TokenService 实现类.
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class ResourceServiceImpl implements ResourceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	String logMsg = "";
	
   @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource read(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int create(Resource role) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Resource role) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Resource> index() {
        // TODO Auto-generated method stub
        return null;
    }
}
