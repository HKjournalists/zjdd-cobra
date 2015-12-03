package com.zaijiadd.app.user.dao;


import java.util.Map;

import com.zaijiadd.app.user.entity.UserInfoEntity;

public interface ShopUserInfoDAO {
	
	public UserInfoEntity getUserInfoById( Integer userId );
	
	public UserInfoEntity getUserInfoByLeaderOrg( Integer orgId );
	
	UserInfoEntity getUserInfoForLogin(Map<String, String> map);
	
	int insert(UserInfoEntity userInfoEntity);


}
