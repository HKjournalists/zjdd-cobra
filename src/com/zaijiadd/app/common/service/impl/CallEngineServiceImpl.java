package com.zaijiadd.app.common.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.common.service.CallEngineEc2Service;
import com.zaijiadd.app.common.service.CallEngineService;
import com.zaijiadd.app.dataquery.dao.YjsReqMsgDao;
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
	
	@Autowired
	private YjsReqMsgDao yjsReqMsgDao;
	
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
		
		userCallingDetailDao.insertUserCallingDetail( userCallingDetailEntity );
		
		Integer userCallingDetailId = userCallingDetailEntity.getUserCallingDetailId();
		
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

	@Override
	public void handleCallback( JSONObject data ) {
		
		// get calling detail id
		String recordUrl = data.getString( "realUrl" );
		String srcMobile = data.getString( "srcEmpNo" );
		String destMobile = data.getString( "destination" );
		String bridgeTimeDate = data.getString( "bridgeTimeDate" );
		String endTimeDate = data.getString( "endTimeDate" );
		String holdingTime = data.getString( "billableSeconds" );
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "destMobile", destMobile );
		params.put( "srcMobile", srcMobile );
		Integer userCallingDetailId = userCallingDetailDao.getUserCallingDetailIdByCondision( params );
		
		// insert log
		params = new HashMap<String, Object>();
		params.put( "userCallingDetailId", userCallingDetailId );
		params.put( "responseData", data.toString() );
		userCallingDetailDao.insertUserCallingLog( params );
		
		// update calling detail
		UserCallingDetailEntity userCallingDetailEntity = new UserCallingDetailEntity();
		userCallingDetailEntity.setUserCallingDetailId( userCallingDetailId );
		userCallingDetailEntity.setRecordUrl( recordUrl );
		userCallingDetailEntity.setHoldingTime( Integer.parseInt( holdingTime ) );
		userCallingDetailEntity.setStatus( 3 );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		try {
			userCallingDetailEntity.setBeginDate( sdf.parse( bridgeTimeDate ) );
			userCallingDetailEntity.setEndDate( sdf.parse( endTimeDate ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		userCallingDetailDao.updateCallingDetailAferCalling( userCallingDetailEntity );
		
		Integer msgId = userCallingDetailDao.getMsgIdByCallingDetailId( userCallingDetailId );
		
		params = new HashMap<String, Object>();
		params.put( "msgId", msgId );
		try {
			params.put( "callDate", sdf.parse( endTimeDate ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
		yjsReqMsgDao.updatePhoneInfo( params );
		
	}

}
