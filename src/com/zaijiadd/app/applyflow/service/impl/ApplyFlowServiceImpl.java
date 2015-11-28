/**
 * @(#)AppPersonFlowImpl.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                            All rights reserved.
 */

package com.zaijiadd.app.applyflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.ApplyFlowDao;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;

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

	@Override
	public Integer addInviteUser(InviteUserEntity inviteUserEntity) {
		return applyFlowDao.addInviteUser(inviteUserEntity);
	}

}
