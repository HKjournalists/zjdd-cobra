package com.zaijiadd.app.applyflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.StoreInfoDao;
import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;

@Service
public class StoreInfoServiceImpl implements StoreInfoService {

	@Autowired
	private StoreInfoDao storeInfoDao;
	
	@Override
	public int deleteByPrimaryKey(Long storeId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(StoreInfo record) throws Exception {
		return storeInfoDao.insert(record);
	}

	@Override
	public int insertSelective(StoreInfo record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StoreInfo selectByPrimaryKey(Long storeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(StoreInfo record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
