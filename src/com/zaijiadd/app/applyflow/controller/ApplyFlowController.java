/**
 * @(#)InviteUserController.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.zaijiadd.app.applyflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.DateUtils;
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
		param.put("inviteUserId", inviteUserId);
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
	 * 根据inviteUserId查询详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUserById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUserById(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("inviteUserid", jsonRequest.getString("inviteUserid"));
		InviteUserEntity inviteUserEntity = applyFlowService.queryInviteUser(param);
		String visitTime = inviteUserEntity.getVisitTime();
		inviteUserEntity.setVisitTime(DateUtils.getDate(visitTime));
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * 查询用户所有的,分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		List<Map<String, Object>> inviteUserMap = applyFlowService.queryInviteUserMap(param);
		for (Map<String, Object> map : inviteUserMap) {

		}
		param.put("result", inviteUserMap);
		// String visitTime = inviteUserEntity.getVisitTime();
		// inviteUserEntity.setVisitTime(DateUtils.getDate(visitTime));
		// Map<String, Object> entityToMap =
		// ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 模糊查询,分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUserLike", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUserLike(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		param.put("inviteuserName", jsonRequest.getString("inviteuserName"));
		List<Map<String, Object>> inviteUseMap = applyFlowService.queryInviteUserLike(param);
		param.put("result", inviteUseMap);
		return ContainerUtils.buildResSuccessMap(param);

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

	/**
	 * 增加开店申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addApplyStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		ApplyStore applyStore = jsonToaddApplyStore(jsonRequest);
		Integer inviteUserId = applyFlowService.addApplyStore(applyStore);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 查询用户所有的开店权
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllApplyStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		ApplyStore applyStore = applyFlowService.queryAllApplyStore(param);
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(applyStore);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 * @param jsonRequest
	 * @return
	 */

	private ApplyStore jsonToaddApplyStore(JSONObject jsonRequest) {
		ApplyStore applyStore = jsonRequest.parseObject(jsonRequest.toJSONString(), ApplyStore.class);
		return applyStore;
	}
}
