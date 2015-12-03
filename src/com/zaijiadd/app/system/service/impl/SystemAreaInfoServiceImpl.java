package com.zaijiadd.app.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.system.dao.SystemAreaDAO;
import com.zaijiadd.app.system.service.SystemAreaInfoService;

@Service
public class SystemAreaInfoServiceImpl implements SystemAreaInfoService {

	@Autowired
	private SystemAreaDAO systemAreaDao;

	@Override
	public List<Map<String, Object>> getSystemChannelInfo() {

		List<Map<String, Object>> result = systemAreaDao.getBaseChannelInfo(0);

		return result;

	}

}
