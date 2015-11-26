package com.zaijiadd.app.dataquery.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zaijiadd.app.dataquery.entity.AllotLogEntity;

@Repository
public interface AllotLogDao {

	/**
	 * 新增加一条分配信�?
	 * @param allotLog
	 * @return
	 */
	public int addAllotLog(AllotLogEntity allotLog);
	
	public List<AllotLogEntity> getAllotLog( Integer msgId );
	
}
