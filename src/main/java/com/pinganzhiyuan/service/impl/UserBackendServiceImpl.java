package com.pinganzhiyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.UserBackendMapper;
import com.pinganzhiyuan.model.UserBackend;
import com.pinganzhiyuan.model.UserBackendExample;
import com.pinganzhiyuan.service.UserBackendService;

@Service
public class UserBackendServiceImpl implements UserBackendService{

    
    @Autowired
    UserBackendMapper userBackendMapper;
    
    public UserBackend findUser(String username) {
        UserBackendExample example = new UserBackendExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserBackend> selectByExample = userBackendMapper.selectByExample(example);
        if (selectByExample != null) {
            return selectByExample.get(0);
        }
        return null;
    }
}
