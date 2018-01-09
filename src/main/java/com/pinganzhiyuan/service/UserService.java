package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.User;

public interface UserService {

	public void createUserPortrayalByUser(User user);

	public void updateUserPortrayal(List<Client> clients);

}
