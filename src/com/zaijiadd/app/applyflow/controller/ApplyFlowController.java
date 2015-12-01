/**
 * @(#)InviteUserController.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.zaijiadd.app.applyflow.controller;

import java.util.Date;
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
import com.zaijiadd.app.applyflow.entity.ApplyRoleRelation;
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.DateUtils;
import com.zaijiadd.app.common.utils.ParseUtils;

/**
 * 流程申请
 * @author chentao
 * @date 2015年11月28日
 */
@RequestMapping("/applyFlow")
@Controller
public class ApplyFlowController {

	@Autowired
	private ApplyFlowService applyFlowService;

	/**
	 * 增加用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addInviteUserMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInviteUserMsg(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		InviteUserEntity inviteUserEntity = jsonToInviteUserVO(jsonRequest);
		inviteUserEntity.setFuctionSate(1);// 用户信息
		Integer inviteUserId = applyFlowService.addInviteUser(inviteUserEntity);
		param.put("inviteUserId", inviteUserId);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 增加邀约
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addInviteUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInviteUser(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		InviteUserEntity inviteUserEntity = jsonToInviteUserVO(jsonRequest);
		inviteUserEntity.setFuctionSate(2);// 邀约
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
	 * 查询用户信息详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUserMsgById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUserMsgById(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("inviteUserid", jsonRequest.getString("inviteUserid"));
		param.put("fuctionSate", "1");
		InviteUserEntity inviteUserEntity = applyFlowService.queryInviteUser(param);
		String visitTime = inviteUserEntity.getVisitTime();
		inviteUserEntity.setVisitTime(DateUtils.getDate(visitTime));
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * 查询邀约详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUserById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUserById(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("inviteUserid", jsonRequest.getString("inviteUserid"));
		param.put("fuctionSate", "2");
		InviteUserEntity inviteUserEntity = applyFlowService.queryInviteUser(param);
		String visitTime = inviteUserEntity.getVisitTime();
		inviteUserEntity.setVisitTime(DateUtils.getDate(visitTime));
		Map<String, Object> entityToMap = ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(entityToMap);

	}

	/**
	 * 查询所有用户信息,分页map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllInviteUserMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllInviteUserMsg(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		param.put("fuctionSate", "1");
		List<Map<String, Object>> inviteUserMap = applyFlowService.queryInviteUserMap(param);
		for (Map<String, Object> map : inviteUserMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getDateWithPattern(createDate, "yyyy-MM-dd"));
		}
		param.put("result", inviteUserMap);
		// String visitTime = inviteUserEntity.getVisitTime();
		// inviteUserEntity.setVisitTime(DateUtils.getDate(visitTime));
		// Map<String, Object> entityToMap =
		// ContainerUtils.entityToMap(inviteUserEntity);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 查询所有邀约,分页
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
		param.put("fuctionSate", "2");
		List<Map<String, Object>> inviteUserMap = applyFlowService.queryInviteUserMap(param);
		for (Map<String, Object> map : inviteUserMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getDateWithPattern(createDate, "yyyy-MM-dd"));
		}
		param.put("result", inviteUserMap);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 用户信息模糊查询,分页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInviteUserMsgLike", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInviteUserMsgLike(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		param.put("inviteuserName", jsonRequest.getString("inviteuserName"));
		param.put("fuctionSate", "1");
		List<Map<String, Object>> inviteUseMap = applyFlowService.queryInviteUserLike(param);
		for (Map<String, Object> map : inviteUseMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getDateWithPattern(createDate, "yyyy-MM-dd"));
		}
		param.put("result", inviteUseMap);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 邀约模糊查询,分页
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
		param.put("fuctionSate", "2");
		List<Map<String, Object>> inviteUseMap = applyFlowService.queryInviteUserLike(param);
		for (Map<String, Object> map : inviteUseMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getDateWithPattern(createDate, "yyyy-MM-dd"));
		}
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
		Integer addApplyStoreId = applyFlowService.addApplyStore(applyStore);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 审批
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approveApplyStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approveApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		ApplyStore applyStore = jsonToaddApplyStore(jsonRequest);
		Integer addApplyStoreId = applyFlowService.approveApplyStore(applyStore);
		applyFlowService.insertApplyRoleRelation(addApplyStoreId, applyStore);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 * @param jsonRequest
	 * @return
	 */

	private ApplyRoleRelation jsonToaApplyRoleRelation(JSONObject jsonRequest) {
		ApplyRoleRelation applyRoleRelation = jsonRequest.parseObject(jsonRequest.toJSONString(),
				ApplyRoleRelation.class);
		return applyRoleRelation;
	}

	/**
	 * 更新开店申请
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateApplyStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		ApplyStore applyStore = jsonToaddApplyStore(jsonRequest);
		Integer applyStoreId = applyFlowService.updateApplyStore(applyStore);
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
		String userId = jsonRequest.getString("userId");
		param.put("userId", userId);
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		List<Map<String, Object>> applyStoreMap = applyFlowService.queryAllApplyStore(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询用户开店权限详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryApplyStoreDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryApplyStoreDetails(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yjsUserId", jsonRequest.getString("yjsUserId"));
		Map<String, Object> applyStoreMap = applyFlowService.queryApplyStoreDetails(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
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
