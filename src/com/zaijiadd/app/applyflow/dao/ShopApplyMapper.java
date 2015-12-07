package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.dto.ShopVO;
import com.zaijiadd.app.applyflow.entity.ShopApply;

public interface ShopApplyMapper {
	
    int deleteByPrimaryKey(Long shopId);

    long insert(ShopApply record);

    int insertSelective(ShopApply record);

    ShopApply selectByPrimaryKey(Long shopId);
    
    ShopVO selectByShopId(Long shopId);

    int updateByPrimaryKeySelective(ShopApply record);

    int updateByPrimaryKey(ShopApply record);
}