/**
 * @(#)ApplyFlowDao.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc. All
 *                       rights reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.InviteUserEntity;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年11月28日
 */

public interface ApplyFlowDao {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param inviteUserEntity
	 * @return
	 */

	public Integer addInviteUser(InviteUserEntity inviteUserEntity);
}
