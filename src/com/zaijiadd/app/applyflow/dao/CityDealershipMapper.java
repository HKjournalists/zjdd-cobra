/**
 * @(#)CityDealershipMapper.java 2015年12月4日 Copyright 2015 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import java.util.Map;

import com.zaijiadd.app.applyflow.entity.CityDealership;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月4日
 */

public interface CityDealershipMapper {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityId
	 * @return
	 */

	CityDealership getCityMoneyByCityId(Integer cityId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityDealership2
	 */

	void updateCityDealership(CityDealership cityDealership2);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityId
	 * @return
	 */

	Map<String, Object> queryDealershipNumAble(Integer cityId);

}
