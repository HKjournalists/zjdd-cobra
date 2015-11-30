/**
 * @(#)AppPersonFlow.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.zaijiadd.app.applyflow.service;

import java.util.Map;

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

}
