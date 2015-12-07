package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.ShopApply;

public interface ShopApplyMapper {
	
    int deleteByPrimaryKey(Long shopId);

    int insert(ShopApply record);

    int insertSelective(ShopApply record);

    ShopApply selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(ShopApply record);

    int updateByPrimaryKey(ShopApply record);
}