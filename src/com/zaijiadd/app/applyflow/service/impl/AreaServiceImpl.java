package com.zaijiadd.app.applyflow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
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

}
