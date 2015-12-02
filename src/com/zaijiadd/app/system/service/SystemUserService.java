package com.zaijiadd.app.system.service;

import com.zaijiadd.app.user.entity.UserInfoEntity;

public interface SystemUserService {
	
	UserInfoEntity getUserInfoForLogin(String username, String password) throws Exception;

}
