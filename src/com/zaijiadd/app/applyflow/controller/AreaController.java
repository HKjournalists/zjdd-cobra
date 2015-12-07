package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ExcelMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
import com.zaijiadd.app.applyflow.dao.TownMapper;
import com.zaijiadd.app.applyflow.entity.City;
import com.zaijiadd.app.applyflow.entity.Country;
import com.zaijiadd.app.applyflow.entity.Excel;
import com.zaijiadd.app.applyflow.entity.Town;
import com.zaijiadd.app.applyflow.service.AreaService;
import com.zaijiadd.app.common.utils.ContainerUtils;

/**
 * 行政区域
 * @author guofeng
 * 
 */
@RequestMapping("/area")
@RestController
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private TownMapper townMapper;
	@Autowired
	private ExcelMapper excelMapper;
	/**
	 * 省份列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/province/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> provinceList(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("privinceList", this.areaService.selectAll());
			//this.areaService.findCitySellInfo(1125, 1257);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	
	private void xiugai() throws Exception {
		for(int i = 1; i < 5; i ++) {
			List cityList = this.areaService.selectByProvinceId(i);
			for(int j = 0, size = cityList.size(); j < size;j ++) {
				City city = (City) cityList.get(j);
				Country country = new Country();
				country.setCityId(i);
				country.setCountryName(city.getCityName());
				this.countryMapper.insert(country);
				List countryList = this.areaService.selectByCityId(city.getCityId());
				for(int m = 0; m < countryList.size(); m ++) {
					Country country1 = (Country) countryList.get(m);
					Town town = new Town();
					town.setCountryId(country.getCountryId());
					town.setTownName(country1.getCountryName());
					this.townMapper.insert(town);
				}
			}
		}
	}
	/**
	 * 城市列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/city/list/{provinceId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> cityList(@PathVariable Integer provinceId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("cityList", this.areaService.selectByProvinceId(provinceId));
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
		
	}
	/**
	 * 区县列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/country/list/{cityId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> countryList(@PathVariable Integer cityId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("countryList", this.areaService.selectByCityId(cityId));
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
		
	}
	/**
	 * 乡镇列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/town/list/{countryId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> townList(@PathVariable Integer countryId) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			param.put("townList", this.areaService.selectByCountryId(countryId));
		} catch (Exception e) { 
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
		
	}
	
	
	/**
	 * 导入数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> init() {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("allCount", "3");
			List<Excel> list = this.excelMapper.selectByName(map);
			for(int i = 0; i < list.size(); i ++) {
				Excel excel = list.get(i);
				/*City city = this.cityMapper.selectCityByName(excel.getCityName());
				if(city != null) {
					city.setTotalDealership(10);
					city.setAlreadySoldNum(Integer.parseInt(excel.getCount1()));
					if(StringUtils.isNotBlank(excel.getCount2())) {
						city.setDepositNum(Integer.parseInt(excel.getCount2()));
					}
				
					this.cityMapper.updateByPrimaryKeySelective(city);
				}*/
				List<Country> countryList = this.countryMapper.selectByName(excel.getCountryName());
				if(countryList != null) {
					for(Country country: countryList ){
						
					if(country != null) {
						country.setTotalDealership(3);
						country.setAlreadySoldNum(Integer.parseInt(excel.getCount1()));
						if(StringUtils.isNotBlank(excel.getCount2())) {
							country.setDepositNum(Integer.parseInt(excel.getCount2()));
						}
					
						this.countryMapper.updateByPrimaryKeySelective(country);
					}
					}
				}
				
			}
			//param.put("townList", this.areaService.selectByCountryId(countryId));
		} catch (Exception e) { 
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
		
	}
}
