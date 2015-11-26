package com.zaijiadd.app.dataquery.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.dataquery.dao.AllotLogDao;
import com.zaijiadd.app.dataquery.entity.AllotLogEntity;
import com.zaijiadd.app.dataquery.service.AllotLogService;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.entity.UserInfoEntity;

public class AllotLogServiceImpl implements AllotLogService {

	@Autowired
	private AllotLogDao allotLogDao;
	
	@Autowired
	private UserInfoDAO userInfoDao;
	
	@Override
	public int addAllotLog(AllotLogEntity allotLog) {
		return allotLogDao.addAllotLog(allotLog);
	}

	@Override
	public List<Map<String, Object>> getDispatchDetail( Integer msgId ) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		List<AllotLogEntity> dispatchDetailList = allotLogDao.getAllotLog( msgId );
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		
		for ( int i = 0; i < dispatchDetailList.size(); i++ ) {
			
			data = new HashMap<String, Object>();
			
			AllotLogEntity allotLogEntity = dispatchDetailList.get( i );
			Integer allotUserId = allotLogEntity.getAllotUserId();
			Integer allotToUserId = allotLogEntity.getAllotToUserId();
			Integer isLeader = allotLogEntity.getIsGroupLeader();
			
			String toRealname = "";
			
			if ( isLeader == 1 ) {
				// leader
				UserInfoEntity userInfoEntity = userInfoDao.getUserInfoByLeaderOrg( allotToUserId );
				toRealname = userInfoEntity.getRealname();
			} else {
				// not leader
				UserInfoEntity userInfoEntity = userInfoDao.getUserInfoById( allotToUserId );
				toRealname = userInfoEntity.getRealname();
			}
			
			UserInfoEntity userInfoEntity = userInfoDao.getUserInfoById( allotUserId );
			String fromRealname = userInfoEntity.getRealname();
			Date allotTime = allotLogEntity.getAllotTime();
			
			data.put( "fromRealname", fromRealname );
			data.put( "toRealname", toRealname );
			data.put( "allotTime", allotTime );
			
			res.add( data );
		
		}
		
		return res;
			
	}

}
