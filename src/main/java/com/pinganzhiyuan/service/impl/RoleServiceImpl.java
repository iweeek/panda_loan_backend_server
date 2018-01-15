package com.pinganzhiyuan.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.PermissionMapper;
import com.pinganzhiyuan.mapper.RoleMapper;
import com.pinganzhiyuan.model.Role;
import com.pinganzhiyuan.model.RoleExample;
import com.pinganzhiyuan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public int create(Role role) {
		return roleMapper.insert(role);
	}

    @Override
    public int update(Role role) {
        int ret = roleMapper.updateByPrimaryKeySelective(role);
        return ret;
    }
    @Override
    public int delete(Long id) {
        int ret = roleMapper.deleteByPrimaryKey(id);
        return ret;
    }
    
    @Override
    public Role read(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }
    
    @Override
    public List<Role> index() {
        RoleExample example = new RoleExample();
        List<Role> list = roleMapper.selectByExample(example);
        return list;
    }

//	@Override
//	public List<Permission> indexPriv() {
//        List<Permission> list = permissionMapper.selectWithColumnNameByExample();
//		return list;
//	}
//	
//
//    @Override
//    public List<Permission> indexPriv(Integer userId) {
//        
//        List<Permission> list = permissionMapper.selectWithColumnNameByExample();
//        return list;
//    }

}
