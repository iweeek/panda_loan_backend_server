package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Resource;

/**
* token service interface.
* 
* @author x1ny
* @date 2017年5月22日
*/
public interface ResourceService {
	
    int create(Resource resource);
    
    public int update(Resource resource);
    
    public int delete(Long id);
    
    public Resource read(Long id);
    
    public List<Resource> index();
    
}
