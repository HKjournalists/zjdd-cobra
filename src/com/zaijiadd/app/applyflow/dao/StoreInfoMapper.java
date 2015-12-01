package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.StoreInfo;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(Long storeId);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Long storeId);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}