package com.zaijiadd.app.user.dao;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.user.dto.CallingLogDTO;
import com.zaijiadd.app.user.entity.UserCallingDetailEntity;

public interface UserCallingDetailDAO {
	
	public Integer insertUserCallingDetail( UserCallingDetailEntity entity );
	
	public Integer updateUserCallingDetailStatus( Map<String, Object> params );
	
	public Integer getUserCallingDetailIdByCondision( Map<String, Object> params );
	
	public Integer updateCallingDetailAferCalling( UserCallingDetailEntity entity );
	
	public Integer insertUserCallingLog( Map<String, Object> params );
	
	public Integer getMsgIdByCallingDetailId( Integer userCallingDetailId );
	
	public List<CallingLogDTO> queryCallingLog( Integer wId );

}
