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
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.common.utils.ContainerUtils;
import com.zaijiadd.app.common.utils.DateUtils;
import com.zaijiadd.app.common.utils.ParseUtils;
import com.zaijiadd.app.user.entity.UserInfoEntity;
import com.zaijiadd.app.utils.constants.ConstantStorePower;
import com.zaijiadd.app.utils.constants.ConstantsRole;

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
		try {
			JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
			Map<String, Object> param = new HashMap<String, Object>();
			InviteUserEntity inviteUserEntity = jsonToInviteUserVO(jsonRequest);
			inviteUserEntity.setFuctionSate(1);
			UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息

			Integer userId = user.getUserId();
			inviteUserEntity.setYjsUserId(userId);
			Integer inviteUserId = applyFlowService.addInviteUser(inviteUserEntity);
			inviteUserId = inviteUserEntity.getInviteUserid();
			param.put("inviteUserId", inviteUserId);
			return ContainerUtils.buildResSuccessMap(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ContainerUtils.buildResFailMap();
		}

	}

	/**
	 * userId roleId
	 * @param request
	 * @param jsonRequest
	 * @return
	 */

	private UserInfoEntity getUserMsg(HttpServletRequest request, JSONObject jsonRequest) {
		UserInfoEntity user = new UserInfoEntity();
		UserInfoEntity userSeesion = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId;
		Integer roleId;
		if (userSeesion != null) {
			user = userSeesion;
			userId = user.getUserId();
			roleId = user.getRoleId();
		} else {
			userId = jsonRequest.getInteger("userId");
			roleId = jsonRequest.getInteger("roleId");
		}
		user.setRoleId(roleId);
		user.setUserId(userId);
		return user;
	}

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
		inviteUserEntity.setFuctionSate(2);// 邀约

		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer userId = user.getUserId();
		inviteUserEntity.setYjsUserId(userId);
		Integer inviteUserId = applyFlowService.addInviteUser(inviteUserEntity);
		inviteUserId = inviteUserEntity.getInviteUserid();
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
		Map<String, Object> inviteUserEntityDet = applyFlowService.queryInviteUserMsgDet(param);
		param.put("result", inviteUserEntityDet);
		return ContainerUtils.buildResSuccessMap(param);

	}

	/**
	 * 查询邀约记录详情
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
		Map<String, Object> inviteUserEntityDet = applyFlowService.queryInviteUserDet(param);
		Date visitTime = (Date) inviteUserEntityDet.get("visitTime");
		String sysDateStr = DateUtils.getSysDate(visitTime, "yyyy-MM-dd");
		inviteUserEntityDet.put("visitTime", sysDateStr);
		param.put("result", inviteUserEntityDet);
		return ContainerUtils.buildResSuccessMap(param);

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
		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer userId = user.getUserId();

		Map<String, Object> param = new HashMap<String, Object>();
		Integer userState = jsonRequest.getInteger("userState");// 邀约状态
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		param.put("yjsUserId", userId);
		param.put("fuctionSate", "1");
		param.put("userState", userState);

		List<Map<String, Object>> inviteUserMap = applyFlowService.queryInviteUserMap(param);
		for (Map<String, Object> map : inviteUserMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getSysDate(createDate, "yyyy-MM-dd"));
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
		UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		Integer userState = jsonRequest.getInteger("userState");// 邀约状态
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", userId);
		param.put("fuctionSate", "2");
		param.put("userState", userState);
		List<Map<String, Object>> inviteUserMap = applyFlowService.queryInviteUserMap(param);
		for (Map<String, Object> map : inviteUserMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getSysDate(createDate, "yyyy-MM-dd"));
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
		UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId = user.getUserId();

		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", userId);
		param.put("inviteuserName", jsonRequest.getString("inviteuserName"));
		param.put("fuctionSate", "1");
		List<Map<String, Object>> inviteUseMap = applyFlowService.queryInviteUserLike(param);
		for (Map<String, Object> map : inviteUseMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getSysDate(createDate, "yyyy-MM-dd"));
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
		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer userId = user.getUserId();

		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		param.put("yjsUserId", userId);
		param.put("inviteuserName", jsonRequest.getString("inviteuserName"));
		param.put("fuctionSate", "2");
		List<Map<String, Object>> inviteUseMap = applyFlowService.queryInviteUserLike(param);
		for (Map<String, Object> map : inviteUseMap) {
			Date createDate = (Date) map.get("createDate");
			map.put("createDate", DateUtils.getSysDate(createDate, "yyyy-MM-dd"));
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
	 * 邀约记录状态更改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateInviteUserById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateInviteUserById(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		Integer inviteUserid = jsonRequest.getInteger("inviteUserid");
		Integer userState = jsonRequest.getInteger("userState");
		param.put("inviteUserid", inviteUserid);
		param.put("userState", userState);
		Integer inviteUserId = applyFlowService.updateInviteUserById(param);
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
	 * 增加开店申请单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addApplyStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Map<String, Object> param = new HashMap<String, Object>();
		ApplyStore applyStore = jsonToaddApplyStore(jsonRequest);
		Integer userId = user.getUserId();

		applyStore.setYjsUserId(userId);
		Integer addApplyStoreId = applyFlowService.addApplyStore(applyStore);
		Integer applyStoreId = applyStore.getApplyStoreId();
		param.put("applyStoreId", applyStoreId);
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
		Integer applyStoreId = jsonRequest.getInteger("applyStoreId");
		Map<String, Object> applyStoreMap = applyFlowService.queryApplyStoreDetails(applyStoreId);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询订单状态--总
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllApplyStoreSate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStoreSate(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		Integer applyStatus = jsonRequest.getInteger("applyStatus");// 订单状态
		Integer userId = user.getUserId();
		param.put("yjsUserId", userId);
		param.put("applyStatus", applyStatus);
		List<Map<String, Object>> applyStoreOrderMap = applyFlowService.queryAllApplyStoreSate(param);
		param.put("result", applyStoreOrderMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询订单状态--申请成功
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllApplyStoreSateSucc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStoreSateSucc(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		param.put("yjsUserId", userId);
		param.put("applyStatus", ConstantStorePower.apply_state_succ);
		List<Map<String, Object>> applyStoreOrderMap = applyFlowService.queryAllApplyStoreSate(param);
		param.put("result", applyStoreOrderMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询订单状态--申请中
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllApplyStoreSateIn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStoreSateIn(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		param.put("yjsUserId", userId);
		param.put("applyState", ConstantStorePower.apply_state_ready);
		List<Map<String, Object>> applyStoreOrderMap = applyFlowService.queryAllApplyStoreSate(param);
		param.put("result", applyStoreOrderMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询订单状态--失败
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAllApplyStoreSateFail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStoreSateFail(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);

		UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		param.put("yjsUserId", userId);
		param.put("applyState", ConstantStorePower.apply_state_fail);
		List<Map<String, Object>> applyStoreOrderMap = applyFlowService.queryAllApplyStoreSate(param);
		param.put("result", applyStoreOrderMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询财务待审批
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryFinanceApproveStoreTry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryApproveStoreSateTry(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		param.put("whoCheck", ConstantsRole.ROLE_FINANCE);// 谁审批
		List<Map<String, Object>> applyStoreMap = applyFlowService.queryFinanceApproveStoreTry(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询角色需要审批
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryRoleApproveStoreTry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryRoleApproveStoreTry(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);
		Integer whoCheck = jsonRequest.getInteger("whoCheck");
		param.put("whoCheck", whoCheck);// 谁审批
		List<Map<String, Object>> applyStoreMap = applyFlowService.queryRoleApproveStoreTry(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询主管待审批
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryManagersApproveStoreTry", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryManagersApproveStoreTry(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		param.put("whoCheck", ConstantsRole.ROLE_MANAGERS);// 谁审批
		List<Map<String, Object>> applyStoreMap = applyFlowService.queryManagersApproveStoreTry(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 审批(财务,主管)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approveStore", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approveStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();

		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer userId = user.getUserId();
		Integer roleId = user.getRoleId();

		param.put("roleId", roleId);
		param.put("userId", userId);
		param.put("applyStatus", jsonRequest.getInteger("applyStatus"));// 状态
		Integer applyStoreMap = applyFlowService.roleApproveStore(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
	}

	/**
	 * 查询审批信息(主管、财务)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryApproveMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllApplyStore(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		String page = jsonRequest.getString("page");// 当前页
		Integer pageCount = jsonRequest.getInteger("pageCount");// 每页的数量
		param.put("start", (Integer.parseInt(page) - 1) * pageCount);// 从那里开始
		param.put("end", pageCount);

		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer roleId = user.getRoleId();
		param.put("roleId", roleId);
		List<Map<String, Object>> applyStoreMap = applyFlowService.queryApproveMsg(param);
		param.put("result", applyStoreMap);
		return ContainerUtils.buildResSuccessMap(param);
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
	 * 打印合同
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/printContract", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> printContract(HttpServletRequest request) {
		JSONObject jsonRequest = ParseUtils.loadJsonPostRequest(request);
		Map<String, Object> param = new HashMap<String, Object>();
		Integer applyStoreId = jsonRequest.getInteger("applyStoreId");
		UserInfoEntity user = getUserMsg(request, jsonRequest);// 用户信息
		Integer userId = user.getUserId();
		param.put("applyStoreId", applyStoreId);
		param.put("userId", userId);
		List<Map<String, Object>> printApply = applyFlowService.printContract(param);
		param.put("result", printApply);
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
