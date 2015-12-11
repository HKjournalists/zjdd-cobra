package com.zaijiadd.app.dataquery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.dataquery.dao.AllotLogDao;
import com.zaijiadd.app.dataquery.dao.DataChangeLogDAO;
import com.zaijiadd.app.dataquery.dao.YjsReqMsgDao;
import com.zaijiadd.app.dataquery.dto.YjsReqMsgDTO;
import com.zaijiadd.app.dataquery.entity.AllotLogEntity;
import com.zaijiadd.app.dataquery.service.DataQueryService;

public class DataQueryServiceImpl implements DataQueryService {
	@Autowired
	YjsReqMsgDao yjsReqMsgDao;
	
	@Autowired
	private DataChangeLogDAO dataChangeLogDao;
	
	@Autowired
	private AllotLogDao allotLogDao;

	@Override
	public List<Map<String, Object>> queryReqMsg( Map<String, Object> param ) {
		return yjsReqMsgDao.queryByParam( param );
	}

	@Override
	public void updReqMsg( Map<String, Object> param ) {
		yjsReqMsgDao.updReqMsg( param );
	}

	@Override
	public List<Map<String, Object>> groupList( Map<String, Object> param ) {
		return yjsReqMsgDao.groupList( param );
	}

	@Override
	public Map<String, Object> groupInfo( Map<String, Object> param ) {
		return yjsReqMsgDao.groupInfo( param );
	}

	@Override
	public List<Map<String, Object>> userList( Map<String, Object> param ) {
		return yjsReqMsgDao.userList( param );
	}

	@Override
	public Map<String, Object> userInfo( Map<String, Object> param ) {
		return yjsReqMsgDao.userInfo( param );
	}

	@Override
	public List<Map<String, Object>> workCount( Map<String, Object> param ) {
		return yjsReqMsgDao.workCount( param );
	}

	@Override
	public List<Map<String, Object>> workCountUser( Map<String, Object> param ) {
		return yjsReqMsgDao.workCountUser( param );
	}

	@Override
	public void dispatchGroupWork( Map<String, Object> param ) {
		yjsReqMsgDao.dispatchGroupWork( param );
	}

	@Override
	public void dispatchPersonWork( Map<String, Object> param ) {
		yjsReqMsgDao.dispatchPersonWork( param );
	}

	@Override
	public List<Map<String, Object>> dataImport( Map<String, Object> param ) {
		return yjsReqMsgDao.dataImport( param );
	}
	
//	@Override
//	public List<Map<String, Object>> externalDataImport( Map<String, Object> param ) {
//		return externalDataDao.importHomeVisitorData( param );
//	}

	@Override
	public void dataInsert( Map<String, Object> param ) {
		yjsReqMsgDao.dataInsert( param );
	}

	@Override
	public Map<String, Object> timeInfo( Map<String, Object> param ) {
		return yjsReqMsgDao.timeInfo( param );
	}

	@Override
	public Integer queryReqMsgByCount( Map<String, Object> param ) {
		return yjsReqMsgDao.queryByParamCount( param );
	}

	@Override
	public Map<String, Object> getStatusDict() {
		
		List<Map<String, Object>> result = yjsReqMsgDao.getStatusType();
		Map<String, Object> res = new HashMap<String, Object>();
		res.put( "statusTypeList", result );
		
		return res;
	
	}
	
	@Override
	public Boolean addMsg( YjsReqMsgDTO dto ) {
		
		yjsReqMsgDao.insertYjsReqMsg( dto );
		
		return true;
	}

	@Override
	public Boolean recoverMsg( Integer userId, Integer msgId ) {
		
		// 判断user的role
		Integer roleId = yjsReqMsgDao.getUserRoleByUserId( userId );
		Integer queryCount = 0;
		Integer changeStatus = 0;
//		YjsReqMsgDTO yjsReqMsgDto = yjsReqMsgDao.getMsgInfoByMsgId( msgId );
		
		if ( roleId == 1 ) {
			// 回收至组外
			queryCount = yjsReqMsgDao.recoveryMsgByCEO( msgId );
			changeStatus = 0;
		} else if ( roleId == 2 ) {
			// 回收至组内
			queryCount = yjsReqMsgDao.recoveryMsgByLeader( msgId );
			changeStatus = 2;
		} else {
			return false;
		}
		
		if ( queryCount <= 0 ) {
			return false;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "userId", userId );
		params.put( "msgId", msgId );
		params.put( "changeStatus", changeStatus );
		queryCount = dataChangeLogDao.insertStatusChangeLog( params );
		
//		AllotLogEntity entity = new AllotLogEntity();
//		entity.setAllotStatusTypeId( 9 );
//		entity.setAllotUserId( userId );
//		entity.setMsgId( msgId );
//		entity.setIsGroupLeader( 0 );
//		entity.setAllotToUserId( yjsReqMsgDto.getCuser() );
//		allotLogDao.addAllotLog( entity );
		
		if ( queryCount >= 0 ) {
			return true;
		}
		return false;
		
	}

	@Override
	public Boolean isMobileExist( String mobile ) {
		
		Integer msgId = yjsReqMsgDao.getReqMsgIdByMobile( mobile );
		if ( msgId != null && msgId > 0 ) {
			return true;
		}
		
		return false;
		
	}

}
