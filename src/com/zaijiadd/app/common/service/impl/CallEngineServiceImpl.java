package com.zaijiadd.app.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.common.service.CallEngineEc2Service;
import com.zaijiadd.app.common.service.CallEngineService;
import com.zaijiadd.app.user.dao.UserCallingDetailDAO;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.entity.UserCallingDetailEntity;
import com.zaijiadd.app.user.entity.UserInfoEntity;

public class CallEngineServiceImpl implements CallEngineService {

	@Autowired
	private UserInfoDAO userInfoDao;
	
	@Autowired
	private UserCallingDetailDAO userCallingDetailDao;
	
	@Autowired
	private CallEngineEc2Service callEngineEc2Service;
	
	@Override
	public Boolean dial( String destMobile, Integer userId, Integer wId ) {
		
		// get jobId by userId from yjs_user
		UserInfoEntity userInfoEntity = userInfoDao.getUserInfoById( userId );
		Integer jobId = userInfoEntity.getJobId();
		
		UserCallingDetailEntity userCallingDetailEntity = new UserCallingDetailEntity();
		userCallingDetailEntity.setMsgId( wId );
		userCallingDetailEntity.setUserId( userId );
		userCallingDetailEntity.setSrcMobile( jobId.toString() );
		userCallingDetailEntity.setDestMobile( destMobile );
		userCallingDetailEntity.setStatus( 1 );
		
		Integer userCallingDetailId = userCallingDetailDao.insertUserCallingDetail( userCallingDetailEntity );
		
		Integer status = 1;
		if ( callEngineEc2Service.dialEc2( jobId, destMobile ) ) {
			status = 2;
		} else {
			status = 4;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "status", status );
		params.put( "userCallingDetailId", userCallingDetailId );
		
		userCallingDetailDao.updateUserCallingDetailStatus( params );
		
		if ( status == 2 ) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Boolean hangup( String destMobile, Integer userId ) {
		
		// get jobId by userId from yjs_user
		UserInfoEntity userInfoEntity = userInfoDao.getUserInfoById( userId );
		Integer jobId = userInfoEntity.getJobId();
		Boolean res = callEngineEc2Service.hangupEc2( jobId );
		
		return res;
	
	}

}
