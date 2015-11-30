/**
 * @(#)InviteUserController.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.ParseUtils;

/**
 * 邀请人信息登录以及申请
 * @author chentao
 * @date 2015年11月28日
 */
@RequestMapping("/applyFlow")
@Controller
public class ApplyFlowController {

	@Autowired
	private ApplyFlowService applyFlowService;

	/**
	 * 增加邀约记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		InviteUserEntity inviteUserEntity = jsonToInviteUserVO(jsonRequest);
		Integer inviteUserId = applyFlowService.addInviteUser(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 根据用户id和姓名、电话查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("inviteuserName", jsonRequest.getString("inviteuserName"));
		param.put("inviteuserMobile", jsonRequest.getString("inviteuserMobile"));
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		InviteUserEntity inviteUserEntity = applyFlowService.queryInviteUser(param);
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * 查询用户所有的
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		InviteUserEntity inviteUserEntity = applyFlowService.queryInviteUser(param);
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * 更新
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		InviteUserEntity inviteUserEntity = jsonToInviteUserVO(jsonRequest);
		Integer inviteUserId = applyFlowService.updateInviteUser(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 * @param jsonRequest
	 * @return
	 */

	private InviteUserEntity jsonToInviteUserVO(JSONObject jsonRequest) {
		InviteUserEntity inviteUserEntity = jsonRequest.parseObject(jsonRequest.toJSONString(), InviteUserEntity.class);
		return inviteUserEntity;
	}
}
