package com.zaijiadd.app.user.dao;

import java.util.Map;

import com.zaijiadd.app.user.entity.UserCallingDetailEntity;

public interface UserCallingDetailDAO {
	
	public Integer insertUserCallingDetail( UserCallingDetailEntity entity );
	
	public Integer updateUserCallingDetailStatus( Map<String, Object> params );

}
