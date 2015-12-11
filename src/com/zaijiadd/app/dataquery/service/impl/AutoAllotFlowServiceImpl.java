package com.zaijiadd.app.dataquery.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.dataquery.dao.AutoAllotFlowDAO;
import com.zaijiadd.app.dataquery.dao.YjsReqMsgDao;
import com.zaijiadd.app.dataquery.entity.UserAutoAllotSettingEntity;
import com.zaijiadd.app.dataquery.service.AutoAllotFlowService;

public class AutoAllotFlowServiceImpl implements AutoAllotFlowService {

	@Autowired
	private AutoAllotFlowDAO autoAllotFlowDao;
	
	@Autowired
	private YjsReqMsgDao yjsReqMsgDao;
	
	@Override
	public Boolean AutoAllotSetting( Integer userId, String[] allotUserArr ) {

		// 生成批次号
		String batchNum = new Date().getTime() + "" + userId;
		
		// 更新用户自动分配开关
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "updateStatus", 1 );
		yjsReqMsgDao.updateAutoAllotStatus( params );
		
		for ( int i = 0; i < allotUserArr.length; i++ ) {
			
			Integer allotUserId = Integer.parseInt( allotUserArr[i] );
			UserAutoAllotSettingEntity entity = new UserAutoAllotSettingEntity();
			entity.setUserId( userId );
			entity.setAllotUserId( allotUserId );
			entity.setBatchNum( batchNum );
			entity.setSortNum( i + 1 );
			entity.setStatus( 1 );
			
			autoAllotFlowDao.insertUserAutoAllotSetting( entity );
			
		}
		
		return true;
	
	}
	
	@Override
	public Boolean CloseAutoAllot( Integer userId ) {
		
		// 更新用户自动分配开关
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "updateStatus", 0 );
		yjsReqMsgDao.updateAutoAllotStatus( params );
		
		return true;
		
	}

	@Override
	public Integer QueryAutoAllotStatus( Integer userId ) {
		
		return yjsReqMsgDao.queryAutoAllotStatus( userId );
		
	}

	@Override
	public void handleAutoAllotFlow() {
		
		// 查询是否有用户设置了自动分配
		List<Integer> userList = autoAllotFlowDao.getAutoAllotUserList();
		if ( userList.size() <= 0 ) {
			return;
		}
		
		// 遍历用户集合
		for ( int i = 0; i < userList.size(); i++ ) {
			
			Integer userId = userList.get( i );
			
			// 获取用户自动分配的配置信息
			List<UserAutoAllotSettingEntity> settingList = autoAllotFlowDao.getUserAutoAllotSettingList( userId );
			
		}
		
	}

}
