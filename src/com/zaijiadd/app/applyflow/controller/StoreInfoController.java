package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;

/**
 * 店铺开户上架入口
 * @author Guo Gary
 * @create date  2015/12/01 16:00
 * @version <b>1.0.0</b>
 */
@RequestMapping("/storeinfo")
@RestController
public class StoreInfoController {
	
	@Autowired
	private StoreInfoService storeInfoService;
	
	/**
	 * 开户申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInviteUserMsg(StoreInfo info) {
		//JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			this.storeInfoService.insert(info);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}

}
