package com.zaijiadd.app.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface SystemAreaDAO {

	public void insertProviceInfo(Map<String, Object> params);

	public void insertCityInfo(Map<String, Object> params);

	public List<Map<String, Object>> getBaseChannelInfo(Integer pre);

}
