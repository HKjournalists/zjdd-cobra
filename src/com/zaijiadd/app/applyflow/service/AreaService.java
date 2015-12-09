package com.zaijiadd.app.applyflow.service;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.entity.City;
import com.zaijiadd.app.applyflow.entity.Country;
import com.zaijiadd.app.applyflow.entity.Province;
import com.zaijiadd.app.applyflow.entity.Town;

/**
 * 行政区域查询
 * @author Guo Gary
 * @version <b>1.0.0</b>
 * @create time  2015/12/02
 */
public interface AreaService {

	/**
	 * 所有省份
	 * @return
	 * @throws Exception
	 */
	List<Province> selectAll() throws Exception;
	
	 /**
     * 按省份查询城市列表
     * @param proviceId
     * @return
     */
    List<City> selectByProvinceId(Integer provinceId) throws Exception;
    
    /**
     * 按城市查询管辖区县
     * @param cityId
     * @return
     */
    List<Country> selectByCityId(Integer cityId) throws Exception;
    
    /**
     * 按区县查询管辖街道等
     * @param countryId
     * @return
     */
    List<Town> selectByCountryId(Integer countryId) throws Exception;
    
    Map<String, Object> findCitySellInfo(int cityId, int countryId) throws Exception;
    
    void updateCitySellInfo(int cityId, int countryId, int num) throws Exception;
}
