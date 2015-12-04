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
	// 申请类型
	public final static Integer APPLY_TYPE_DEALERSHIP = 0;// 经销权
	public final static Integer APPLY_TYPE_SMALLSTORE = 1;// 小店
	// 申请人类别
	public final static Integer apply_person_type_personal = 0;// 个人
	public final static Integer apply_person_type_company = 1;// 公司
	// 付款方式
	public final static Integer APPLY_PAY_WAY_BANK = 0;// 银行转账
	public final static Integer APPLY_PAY_WAY_ALIPAY = 1;// 支付宝
	public final static Integer APPLY_PAY_WAY_SWIPING_CARD = 2;// 刷卡
	public final static Integer APPLY_PAY_WAY_CASH = 3;// 现金
	// 付款类型
	public final static Integer APPLY_PAYMONEY_NOTALL = 0;// 定金
	public final static Integer APPLY_PAYMONEY_ALL = 1;// 全额

}
