package com.zaijiadd.app.applyflow.dao;

import java.util.ArrayList;
import java.util.List;

import com.zaijiadd.app.applyflow.entity.City;

public interface CityMapper {

	int deleteByPrimaryKey(Integer cityId);

	String selectNameById(Integer cityId);

	int insert(City record);

	int insertSelective(City record);

	/**
	 * 按省份查询城市列表
	 * @param proviceId
	 * @return
	 */
	List<City> selectByProvinceId(Integer proviceId);

	int updateByPrimaryKeySelective(City record);

	int updateByPrimaryKey(City record);
	
	City selectCityByName(String name);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityId
	 * @return
	 */

	ArrayList<City> selectCityByID(Integer cityId);
	
	int selectSpecialByCityId(Integer cityId);

}
