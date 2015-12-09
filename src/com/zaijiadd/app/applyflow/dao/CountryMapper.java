package com.zaijiadd.app.applyflow.dao;

import java.util.List;

import com.zaijiadd.app.applyflow.entity.Country;

public interface CountryMapper {
	
    int deleteByPrimaryKey(Integer countryId);
    
    String selectNameById(Integer countryId);

    int insert(Country record);

    int insertSelective(Country record);

    /**
     * 按城市查询管辖区县
     * @param cityId
     * @return
     */
    List<Country> selectByCityId(Integer cityId);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
    
    List<Country> selectByName(String selectByName);
    
    List<Country> selectByCounryId(int countryId);
}