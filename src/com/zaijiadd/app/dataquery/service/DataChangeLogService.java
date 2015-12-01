package com.zaijiadd.app.dataquery.service;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.user.dto.CallingLogDTO;

public interface DataChangeLogService {
	
	public Boolean addRemarkChangeLog( Integer userId, String remark, Integer wId );
	
	public Boolean addStatusTypeChangeLog( Integer userId, Integer statusType, Integer wId );

	public List<Map<String, Object>> queryRemarkChangeLog( Integer wId );
	
	public List<Map<String, Object>> queryStatusChangeLog( Integer wId );
	
	public List<CallingLogDTO> queryCallingLog( Integer wId );
	
}
