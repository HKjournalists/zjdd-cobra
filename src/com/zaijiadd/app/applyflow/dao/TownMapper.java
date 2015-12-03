package com.zaijiadd.app.applyflow.dao;

import java.util.List;

import com.zaijiadd.app.applyflow.entity.Town;

public interface TownMapper {
	
    int deleteByPrimaryKey(Integer townId);
    
    String selectNameById(Integer townId);

    int insert(Town record);

    int insertSelective(Town record);

    /**
     * 按区县查询管辖街道等
     * @param countryId
     * @return
     */
    List<Town> selectByCountryId(Integer countryId);

    int updateByPrimaryKeySelective(Town record);

    int updateByPrimaryKey(Town record);
}