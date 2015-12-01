/**
 * @(#)AppPersonFlowImpl.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                            All rights reserved.
 */

package com.zaijiadd.app.applyflow.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.ApplyFlowDao;
import com.zaijiadd.app.applyflow.entity.ApplyRoleRelation;
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.user.entity.UserInfoEntity;
import com.zaijiadd.app.utils.constants.ConstantsRole;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年11月28日
 */
@Service
public class ApplyFlowServiceImpl implements ApplyFlowService {

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#addInviteUser(com.zaijiadd.app.applyflow.entity.InviteUserEntity)
	 */
	@Autowired
	ApplyFlowDao applyFlowDao;
	@Autowired
	private UserInfoDAO userInfoDao;

	@Override
	public Integer addInviteUser(InviteUserEntity inviteUserEntity) {
		return applyFlowDao.addInviteUser(inviteUserEntity);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateInviteUser(com.zaijiadd.app.applyflow.entity.InviteUserEntity)
	 */

	@Override
	public Integer updateInviteUser(InviteUserEntity inviteUserEntity) {
		applyFlowDao.updateInviteUser(inviteUserEntity);
		return null;
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUser(java.util.Map)
	 */

	@Override
	public InviteUserEntity queryInviteUser(Map<String, Object> param) {
		return applyFlowDao.queryInviteUser(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#addApplyStore(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer addApplyStore(ApplyStore applyStore) {
		Integer dealershipNum = applyStore.getDealershipNum();
		this.getCityDealershipMoney(dealershipNum);
		BigDecimal paidMoney = applyStore.getPaidMoney();
		BigDecimal needPaymoney = applyStore.getNeedPaymoney();
		BigDecimal needPaymoneyCount = paidMoney.add(needPaymoney);
		if (2 < 3) {// 实际付的金额比应收的金额小，那么给主管审批
			applyStore.setWhoCheck(ConstantsRole.ROLE_MANAGERS);
		} else {// 实际付的金额比应收的金额 相等，给财务
			applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
		}
		return applyFlowDao.addApplyStore(applyStore);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryAllApplyStore(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryAllApplyStore(Map<String, Object> param) {
		String userId = (String) param.get("userId");
		UserInfoEntity userInfoEntity = userInfoDao.getUserInfoById(Integer.valueOf(userId));
		Integer roleId = userInfoEntity.getRoleId();
		param.put("", "");
		return applyFlowDao.queryAllApplyStore(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserLike(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryInviteUserLike(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserLike(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserMap(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryInviteUserMap(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserMap(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateApplyStore(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer updateApplyStore(ApplyStore applyStore) {
		// TODO 该方法尚未实现
		return null;
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryApplyStoreDetails(java.util.Map)
	 */

	@Override
	public Map<String, Object> queryApplyStoreDetails(Map<String, Object> param) {
		// TODO 该方法尚未实现
		return null;
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#getCityDealershipMoney(java.lang.Integer)
	 */

	@Override
	public void getCityDealershipMoney(Integer dealershipNum) {
		// TODO 该方法尚未实现

	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#insertApplyRoleRelation(java.lang.Integer,
	 *      com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public void insertApplyRoleRelation(Integer addApplyStoreId, ApplyStore applyStore) {
		ApplyRoleRelation applyRoleRelation = new ApplyRoleRelation();
		if (2 < 3) {// 实际付的金额比应收的金额小，那么给主管审批
			Integer yjsUserId = applyStore.getYjsUserId();
			applyRoleRelation.setApplyStoreId(addApplyStoreId);
			applyRoleRelation.setRoleid(ConstantsRole.ROLE_MANAGERS);
			applyRoleRelation.setApplyState(0);
			applyFlowDao.insertApplyRoleRelation(applyRoleRelation);
		} else {// 实际付的金额比应收的金额 相等，给财务
			Integer yjsUserId = applyStore.getYjsUserId();
			applyRoleRelation.setApplyStoreId(addApplyStoreId);
			applyRoleRelation.setRoleid(ConstantsRole.ROLE_FINANCE);
			applyRoleRelation.setApplyState(0);
			applyFlowDao.insertApplyRoleRelation(applyRoleRelation);
		}

	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#approveApplyStore(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer approveApplyStore(ApplyStore applyStore) {
		Integer yjsUserId = applyStore.getYjsUserId();
		if (ConstantsRole.ROLE_MANAGERS.equals(yjsUserId)) {
			applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
			applyStore.setApplyStatus(0);

		} else if (ConstantsRole.ROLE_FINANCE.equals(yjsUserId)) {
			applyStore.setApplyStatus(1);
		}
		return applyFlowDao.updateApplyStore(applyStore);
	}
}
