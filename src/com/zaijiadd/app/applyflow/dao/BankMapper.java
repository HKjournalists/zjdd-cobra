/**
 * @(#)BankMapper.java 2015年12月5日 Copyright 2015 it.kedacom.com, Inc. All rights
 *                     reserved.
 */

package com.zaijiadd.app.applyflow.dao;

import java.util.List;
import java.util.Map;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月5日
 */

public interface BankMapper {

	/**
	 * (用一句话描述方法的主要功能)
	 * @param param
	 * @return
	 */

	List<Map<String, Object>> queryBankList(Map<String, Object> param);

}
