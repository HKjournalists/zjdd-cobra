/**
 * @(#)ApplyStoreDao.java 2015年12月3日 Copyright 2015 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.entity.ApplyStore;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月3日
 */

public interface ApplyStoreDao {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer addApplyStore(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStoreId
	 * @return
	 */

	Map<String, Object> queryApplyStoreDetails(Integer applyStoreId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> applyStoreDao(Map<String, Object> param);

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

	List<Map<String, Object>> queryAllApplyStoreSate(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer updateApplyStore(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryApproveMsg(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Map<String, Object> printContract(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryRoleApproveStoreTry(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStore
	 * @return
	 */

	Integer updateWhetherStartApply(ApplyStore applyStore);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<ApplyStore> queryApplStoreNotAllMoney(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyStoreId
	 * @return
	 */

	ApplyStore selectByAppStoreId(Integer applyStoreId);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryAllApplyStoreSateIn(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Integer queryByParamCount(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryApplyDealershipNum(Map<String, Object> param);

}
