package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Permission;

/**
* token service interface.
* 
* @author x1ny
* @date 2017年5月22日
*/
public interface PermissionService {
	
    int create(Permission role);
    
    public int update(Permission role);
    
    public int delete(Long id);
    
    public Permission read(Long id);
    
    public List<Permission> index();
    
}
