/**
 * @(#)AppPersonFlow.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.zaijiadd.app.applyflow.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年11月28日
 */

public interface ApplyFlowService {

	/**
	 * 添加邀约人
	 * @param inviteUserEntity
	 * @return
	 */

	Integer addInviteUser(InviteUserEntity inviteUserEntity);

	/**
	 * 更新邀约人信息
	 * @param inviteUserEntity
	 * @return
	 */

	Integer updateInviteUser(InviteUserEntity inviteUserEntity);

	/**
	 * 查询邀约人信息，根据手机号和姓名
	 * @param inviteUserEntity
	 * @return
	 */

	InviteUserEntity queryInviteUser(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer addApplyStore(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryAllApplyStore(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryInviteUserLike(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryInviteUserMap(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer updateApplyStore(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStoreId
	 * @return
	 */

	Map<String, Object> queryApplyStoreDetails(Integer applyStoreId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityId
	 * @return
	 */

	BigDecimal getCityDealershipMoney(Integer cityId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryAllApplyStoreSate(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryFinanceApproveStoreTry(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryManagersApproveStoreTry(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Integer roleApproveStore(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryApproveMsg(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param userId
	 * @return
	 */

	Map<String, Object> printContract(Map<String, Object> userId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Map<String, Object> queryInviteUserDet(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Map<String, Object> queryInviteUserMsgDet(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Integer updateInviteUserById(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryRoleApproveStoreTry(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryBankList(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer updateWhetherStartApply(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param cityId
	 * @return
	 */

	Map<String, Object> queryDealershipNumAble(Integer cityId);

}
