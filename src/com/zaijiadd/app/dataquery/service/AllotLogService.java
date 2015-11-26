package com.zaijiadd.app.dataquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zaijiadd.app.dataquery.entity.AllotLogEntity;

@Service
public interface AllotLogService {

	public int addAllotLog(AllotLogEntity allotLog);
	
	public List<Map<String, Object>> getDispatchDetail( Integer msgId );
	
}
