package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

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

}
