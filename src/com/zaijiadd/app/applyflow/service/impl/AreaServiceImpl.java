package com.zaijiadd.app.applyflow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
import com.zaijiadd.app.applyflow.dao.SpecialCityMapper;
import com.zaijiadd.app.applyflow.dao.TownMapper;
import com.zaijiadd.app.applyflow.entity.City;
import com.zaijiadd.app.applyflow.entity.Country;
import com.zaijiadd.app.applyflow.entity.Province;
import com.zaijiadd.app.applyflow.entity.Town;
import com.zaijiadd.app.applyflow.service.AreaService;

public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private TownMapper townMapper;
	@Autowired
	private SpecialCityMapper specialCityMapper;
	
	@Override
	public List<Province> selectAll() throws Exception {
		return provinceMapper.selectAll();
	}

	@Override
	public List<City> selectByProvinceId(Integer proviceId) throws Exception {
		return cityMapper.selectByProvinceId(proviceId);
	}

	@Override
	public List<Country> selectByCityId(Integer cityId) throws Exception {
		return countryMapper.selectByCityId(cityId);
	}

	@Override
	public List<Town> selectByCountryId(Integer countryId) throws Exception {
		return townMapper.selectByCountryId(countryId);
	}

	@Override
	public Map<String, Object> findCitySellInfo(int cityId, int countryId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		if(this.cityMapper.selectSpecialByCityId(cityId) == 1) {
			List<City> cityList = this.cityMapper.selectCityByID(cityId);
			if(cityList != null && cityList.size() > 0) {
				City city = cityList.get(0);
				resultMap.put("money", city.getCityMoney());
				resultMap.put("laveNum", city.getTotalDealership()  - (city.getAlreadySoldNum() == null ? 0:city.getAlreadySoldNum()));
			}
		} else {
			List<Country> countryList = this.countryMapper.selectByCounryId(countryId);
			if(countryList != null && countryList.size() > 0) {
				Country country = countryList.get(0);
				resultMap.put("money", country.getCountryMoney());
				resultMap.put("laveNum", country.getTotalDealership()  - (country.getAlreadySoldNum() == null ? 0:country.getAlreadySoldNum()));
			}
		}
		return resultMap;
	}

}
