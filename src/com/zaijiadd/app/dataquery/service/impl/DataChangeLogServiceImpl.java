package com.zaijiadd.app.dataquery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.dataquery.dao.DataChangeLogDAO;
import com.zaijiadd.app.dataquery.service.DataChangeLogService;
import com.zaijiadd.app.user.dao.UserCallingDetailDAO;
import com.zaijiadd.app.user.dto.CallingLogDTO;

public class DataChangeLogServiceImpl implements DataChangeLogService {

	@Autowired
	private DataChangeLogDAO dataChangeLogDao;
	
	@Autowired
	private UserCallingDetailDAO userCallingDetailDao;
	
	@Override
	public Boolean addRemarkChangeLog( Integer userId, String remark, Integer wId ) {
		
		if ( remark == null || remark.trim().equals( "" ) ) {
			return true;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "changeRemark", remark );
		params.put( "msgId", wId );
		
		dataChangeLogDao.insertRemarkChangeLog( params );
		
		return true;
	
	}

	@Override
	public Boolean addStatusTypeChangeLog( Integer userId, Integer statusType, Integer wId ) {
		
		if ( statusType == null ) {
			return true;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "changeStatus", statusType );
		params.put( "msgId", wId );
		
		dataChangeLogDao.insertStatusChangeLog( params );
		
		return true;
	}

	@Override
	public List<Map<String, Object>> queryRemarkChangeLog( Integer wId ) {
		
		return dataChangeLogDao.queryRemarkChangeLog( wId );
		
	}

	@Override
	public List<Map<String, Object>> queryStatusChangeLog( Integer wId ) {
		
		return dataChangeLogDao.queryStatusChangeLog( wId );
		
	}

	@Override
	public List<CallingLogDTO> queryCallingLog( Integer wId ) {
		
		return userCallingDetailDao.queryCallingLog( wId );
		
	}

}
