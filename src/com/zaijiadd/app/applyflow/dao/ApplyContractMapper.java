/**
 * @(#)ApplyContractMapper.java 2015年12月7日 Copyright 2015 it.kedacom.com, Inc.
 *                              All rights reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import java.util.Map;

import com.zaijiadd.app.applyflow.entity.ApplyContract;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月7日
 */

public interface ApplyContractMapper {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyContractParam
	 * @return
	 */

	ApplyContract getApplyContract(Map<String, Object> applyContractParam);

}
