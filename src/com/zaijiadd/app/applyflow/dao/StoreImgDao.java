package com.zaijiadd.app.applyflow.dao;

import java.util.List;

import com.zaijiadd.app.applyflow.entity.StoreImg;

public interface StoreImgDao {
	
    int deleteByPrimaryKey(Long imgId);

    int insert(StoreImg record);

    int insertSelective(StoreImg record);

    StoreImg selectByPrimaryKey(Long imgId);
    
    List<StoreImg> selectByStoreId(Long storeId);

    int updateByPrimaryKeySelective(StoreImg record);

    int updateByPrimaryKey(StoreImg record);
    
    
}