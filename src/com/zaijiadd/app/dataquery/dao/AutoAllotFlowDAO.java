package com.zaijiadd.app.dataquery.dao;

import java.util.List;

import com.zaijiadd.app.dataquery.entity.UserAutoAllotSettingEntity;

public interface AutoAllotFlowDAO {
	
	public Integer insertUserAutoAllotSetting( UserAutoAllotSettingEntity entity );
	
	public List<Integer> getAutoAllotUserList();
	
	public List<UserAutoAllotSettingEntity> getUserAutoAllotSettingList( Integer UserId ); 

}
