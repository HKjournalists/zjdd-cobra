package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.SpecialCity;

public interface SpecialCityMapper {
	
    int deleteByPrimaryKey(Integer cityId);

    int insert(SpecialCity record);

    int insertSelective(SpecialCity record);

    SpecialCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(SpecialCity record);

    int updateByPrimaryKey(SpecialCity record);
}