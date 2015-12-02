package com.zaijiadd.app.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.system.service.SystemUserService;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.entity.UserInfoEntity;

@Service
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserInfoEntity getUserInfoForLogin(String username, String password) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		return userInfoDAO.getUserInfoForLogin(map);
	}

}
