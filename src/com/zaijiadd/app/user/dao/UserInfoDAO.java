package com.zaijiadd.app.user.dao;


import java.util.Map;

import com.zaijiadd.app.user.entity.UserInfoEntity;

public interface UserInfoDAO {
	
	public UserInfoEntity getUserInfoById( Integer userId );
	
	public UserInfoEntity getUserInfoByLeaderOrg( Integer orgId );
	
	UserInfoEntity getUserInfoForLogin(Map<String, String> map);
	
	UserInfoEntity getLeader(Map<String, Object> map);
	
	UserInfoEntity getLeaderByOrgId(Integer orgId);

	
	void changePassword(UserInfoEntity userInfoEntity);
	
}
