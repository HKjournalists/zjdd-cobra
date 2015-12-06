package com.zaijiadd.app.system.service;

import com.zaijiadd.app.user.entity.UserInfoEntity;

public interface SystemUserService {
	
	UserInfoEntity getUserInfoForLogin(String username, String password) throws Exception;
	
	void changePassword(int userId, String password, String newPassword) throws Exception;
	
	UserInfoEntity registerUser(String mobile) throws Exception;
	
	UserInfoEntity getLeader(Integer userId) throws Exception;

}
