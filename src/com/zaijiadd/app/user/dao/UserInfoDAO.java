package com.zaijiadd.app.user.dao;


import com.zaijiadd.app.user.entity.UserInfoEntity;

public interface UserInfoDAO {
	
	public UserInfoEntity getUserInfoById( Integer userId );

}