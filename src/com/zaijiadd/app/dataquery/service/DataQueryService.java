package com.zaijiadd.app.dataquery.service;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.dataquery.dto.YjsReqMsgDTO;

public interface DataQueryService {
	
	/**查询数据*/
	public List<Map<String,Object>> queryReqMsg(Map<String,Object> param);
	/**查询总数据量*/
	public Integer queryReqMsgByCount(Map<String,Object> param);
	/**更新数据*/
	public void updReqMsg(Map<String,Object> param);
	/**查询用户组信息*/
	public List<Map<String,Object>> groupList(Map<String,Object> param);
	/**查询组信息*/
	public Map<String,Object> groupInfo(Map<String,Object> param);
	/**查询任务数*/
	public List<Map<String,Object>> workCount(Map<String,Object> param);
	/**查询用户任务数*/
	public List<Map<String,Object>> workCountUser(Map<String,Object> param);
	/**查询用户信息*/
	public List<Map<String,Object>> userList(Map<String,Object> param);
	/**查询用户信息*/
	public Map<String,Object> userInfo(Map<String,Object> param);
	/**分配信息到组*/
	public void dispatchGroupWork(Map<String,Object> param);
	/**分配信息到用户*/
	public void dispatchPersonWork(Map<String,Object> param);
	
	/**数据导入*/
	public List<Map<String,Object>> dataImport(Map<String,Object> param);
	public void dataInsert(Map<String, Object> param);
	public Map<String, Object> timeInfo(Map<String, Object> param);
	
	public Map<String, Object> getStatusDict();
	
	public Boolean addMsg( YjsReqMsgDTO dto );
	
	public Boolean recoverMsg( Integer userId, Integer msgId );
	
	public Boolean isMobileExist( String mobile );
	
//	List<Map<String, Object>> externalDataImport( Map<String, Object> param );
	
}
