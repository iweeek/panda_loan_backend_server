package com.pinganzhiyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;
import com.pinganzhiyuan.util.UserService;


/**
* TokenService 实现类.
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

	@Override
	public List<User> search(User user) {
		return null;
	}
    
    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
       
    }

    @Override
    public User read(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public String getAvatarUrl(String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public List<User> index() {
//        List<User> list = userMapper.index();
//        return list;
//    }

    @Override
    public int create(User user) {
        int ret = userMapper.insert(user);
        return ret;
    }

    @Override
    public User findUser(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> selectByExample = userMapper.selectByExample(example);
        if (selectByExample != null) {
            return selectByExample.get(0);
        }
        return null;
    }
	
}
