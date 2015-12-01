package com.zaijiadd.app.applyflow.dao;

import org.springframework.stereotype.Repository;

import com.zaijiadd.app.applyflow.entity.StoreInfo;

@Repository
public interface StoreInfoDao {
	
    int deleteByPrimaryKey(Long storeId);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Long storeId);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}