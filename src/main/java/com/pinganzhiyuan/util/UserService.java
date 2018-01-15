package com.pinganzhiyuan.util;

import java.util.List;

import com.pinganzhiyuan.model.User;


public interface UserService {
    
    List<User> search(User user);

    @SuppressWarnings("rawtypes")
    int update(User user);
    
    int create(User user);
    
    User read(Long id);
    
    User findUser(String username);
    
    String getAvatarUrl(String fileName);

//    List<User> index();
}
