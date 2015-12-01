package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.StoreImg;

public interface StoreImgMapper {
    int deleteByPrimaryKey(Long imgId);

    int insert(StoreImg record);

    int insertSelective(StoreImg record);

    StoreImg selectByPrimaryKey(Long imgId);

    int updateByPrimaryKeySelective(StoreImg record);

    int updateByPrimaryKey(StoreImg record);
}