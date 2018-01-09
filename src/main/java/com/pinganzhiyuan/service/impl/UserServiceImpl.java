package com.pinganzhiyuan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.UserPortrayalMapper;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserPortrayal;
import com.pinganzhiyuan.model.UserPortrayalExample;
import com.pinganzhiyuan.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserPortrayalMapper userPortrayalMapper;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Override
	public void createUserPortrayalByUser(User user) {
		// TODO Auto-generated method stub
		UserPortrayal userPortrayal = new UserPortrayal();
		userPortrayal.setUserId(user.getId());
		userPortrayal.setCellPhone(user.getUsername());
		userPortrayal.setRegistTime(new Date(user.getRegistTime()));
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andUserIdEqualTo(user.getId());
		if (clientMapper.selectByExample(clientExample).size() != 0) {
			Client client = clientMapper.selectByExample(clientExample).get(0);
			userPortrayal.setIsApprove(true);
			userPortrayal.setApproveTime(client.getCreatedAt());
			StringBuilder birthDate = new StringBuilder(client.getIdentityNo().substring(6, 14));
			birthDate.insert(6, "-");
			birthDate.insert(4, "-");
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			try {
				userPortrayal.setBirthDate(sdf.parse(birthDate.toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int is_man =Integer.valueOf(client.getIdentityNo().substring(16,17));
			if (is_man%2 == 0) {
				userPortrayal.setIsMan(false);
			} else if (is_man%2 == 1) {
				userPortrayal.setIsMan(true);
			}
			userPortrayal.setPrefixOfIdentity(client.getIdentityNo().substring(0,6));
			userPortrayal.setEducation(client.getEducation());
			userPortrayal.setProfession(client.getProfession());
			userPortrayal.setGuarantee(client.getGuarantee());
		} else {
			userPortrayal.setIsApprove(false);
		}
		UserPortrayalExample userPortrayalExample = new UserPortrayalExample();
		userPortrayalExample.createCriteria().andUserIdEqualTo(userPortrayal.getUserId());
		if (userPortrayalMapper.selectByExample(userPortrayalExample).size() == 0) {
			userPortrayal.setCreatedAt(new Date());
			userPortrayalMapper.insert(userPortrayal);
		} else {
			userPortrayal.setId(userPortrayalMapper.selectByExample(userPortrayalExample).get(0).getId());
			userPortrayal.setCreatedAt(userPortrayalMapper.selectByExample(userPortrayalExample).get(0).getCreatedAt());
			userPortrayalMapper.updateByPrimaryKey(userPortrayal);
		}
	}
	@Override
	public void updateUserPortrayal(List<Client> clients) {
		// TODO Auto-generated method stub
		for (Client client : clients) {
			UserPortrayalExample example = new UserPortrayalExample();
			example.createCriteria().andUserIdEqualTo(client.getUserId());
			if (userPortrayalMapper.selectByExample(example).size() != 0) {
				UserPortrayal userPortrayal = userPortrayalMapper.selectByExample(example).get(0);
				userPortrayal.setIsApprove(true);
				userPortrayal.setApproveTime(client.getCreatedAt());
				StringBuilder birthDate = new StringBuilder(client.getIdentityNo().substring(6, 14));
				birthDate.insert(6, "-");
				birthDate.insert(4, "-");
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
				try {
					userPortrayal.setBirthDate(sdf.parse(birthDate.toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int is_man =Integer.valueOf(client.getIdentityNo().substring(16,17));
				if (is_man%2 == 0) {
					userPortrayal.setIsMan(false);
				} else if (is_man%2 == 1) {
					userPortrayal.setIsMan(true);
				}
				userPortrayal.setPrefixOfIdentity(client.getIdentityNo().substring(0,6));
				userPortrayal.setEducation(client.getEducation());
				userPortrayal.setProfession(client.getProfession());
				userPortrayal.setGuarantee(client.getGuarantee());
				userPortrayalMapper.updateByPrimaryKey(userPortrayal);
			}	
		}
	}

}
