package com.zaijiadd.app.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.system.service.SystemUserService;
import com.zaijiadd.app.user.dto.UserInfoDTO;
import com.zaijiadd.app.user.entity.UserInfoEntity;

@RestController
@Api(value = "用户API", description = "用户相关API")
public class IndexController {
	
	@Autowired
	private SystemUserService systemUserService;
	/**
	 * 用户登录
	 * @param request
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "创建用户")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest( request );
			String username = jsonRequest.getString("username");
			String password = jsonRequest.getString("password");
			UserInfoEntity userInfoEntity = systemUserService.getUserInfoForLogin(username, password);
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			if(userInfoEntity == null) {
				return ContainerUtils.buildResFailMap();
			}
			PropertyUtils.copyProperties(userInfoDTO, userInfoEntity);
			request.getSession().setAttribute("user", userInfoEntity);
			param.put("userInfo", userInfoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
	/**
	 * 用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerUser(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String mobile = request.getParameter("mobile");
			UserInfoEntity userInfoEntity = systemUserService.registerUser(mobile);
			request.getSession().setAttribute("user", userInfoEntity);
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			if(userInfoEntity == null) {
				return ContainerUtils.buildResFailMap();
			}
			PropertyUtils.copyProperties(userInfoDTO, userInfoEntity);
			param.put("userInfo", userInfoDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);
		
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginOut(HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(null);

	}

	/**
	 * 密码修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
		    systemUserService.changePassword(((UserInfoEntity)request.getSession().getAttribute("user")).getUserId(), password, newPassword);;
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}
		return ContainerUtils.buildResSuccessMap(param);

	}
}
