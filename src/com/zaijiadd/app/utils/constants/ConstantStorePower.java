/**
 * @(#)ConstantsRole.java 2015年12月1日 Copyright 2015 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.zaijiadd.app.utils.constants;

import java.math.BigDecimal;

/**
 * (用一句话描述类的主要功能)
 * @author chentao
 * @date 2015年12月1日
 */

public class ConstantStorePower {

	// 单子申请状态
	public final static Integer apply_state_ready = 0;//
	public final static Integer apply_state_succ = 1;//
	public final static Integer apply_state_fail = 2;//

	public final static Integer approve_state_ready = 0;//
	public final static Integer approve_state_succ = 1;//
	public final static Integer approve_state_fail = 2;//

	public final static BigDecimal STORE_MONEY = new BigDecimal(70000);//
	public final static Integer APPLY_TYPE_DEALERSHIP = 0;// 经销权
	public final static Integer apply_type_SMALLSTORE = 1;// 小店

}
