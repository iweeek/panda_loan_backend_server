package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Role;

public interface RoleService {

	int create(Role role);
	
	public int update(Role role);
	
	public int delete(Long id);
	
	public Role read(Long id);
	
    public List<Role> index();
    
//    public List<XPWPriv> indexPriv();
//
//    List<XPWPriv> indexPriv(Integer userId);
}
