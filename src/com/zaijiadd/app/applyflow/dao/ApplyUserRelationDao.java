/**
 * @(#)ApplyUserRelationDao.java 2015年12月3日 Copyright 2015 it.kedacom.com, Inc.
 *                               All rights reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.entity.ApplyUserRelation;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月3日
 */

public interface ApplyUserRelationDao {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryApproveMsg(Map<String, Object> param);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyUserRelation
	 */

	void insertApplyRoleRelation(ApplyUserRelation applyUserRelation);

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	Integer queryApproveMsgCount(Map<String, Object> param);

}
