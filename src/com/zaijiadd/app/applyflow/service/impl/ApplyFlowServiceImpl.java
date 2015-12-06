/**
 * @(#)AppPersonFlowImpl.java 2015年11月28日 Copyright 2015 it.kedacom.com, Inc.
 *                            All rights reserved.
 */

package com.zaijiadd.app.applyflow.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.ApplyFlowDao;
import com.zaijiadd.app.applyflow.dao.ApplyStoreDao;
import com.zaijiadd.app.applyflow.dao.ApplyUserRelationDao;
import com.zaijiadd.app.applyflow.dao.BankMapper;
import com.zaijiadd.app.applyflow.dao.CityDealershipMapper;
import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.entity.ApplyStore;
import com.zaijiadd.app.applyflow.entity.ApplyUserRelation;
import com.zaijiadd.app.applyflow.entity.City;
import com.zaijiadd.app.applyflow.entity.CityDealership;
import com.zaijiadd.app.applyflow.entity.InviteUserEntity;
import com.zaijiadd.app.applyflow.service.ApplyFlowService;
import com.zaijiadd.app.user.dao.UserInfoDAO;
import com.zaijiadd.app.utils.constants.ConstantStorePower;
import com.zaijiadd.app.utils.constants.ConstantsRole;

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
	@Autowired
	ApplyStoreDao applyStoreDao;
	@Autowired
	BankMapper bankMapper;
	@Autowired
	CityDealershipMapper cityDealershipMapper;
	@Autowired
	ApplyUserRelationDao applyUserRelationDao;
	@Autowired
	private UserInfoDAO userInfoDao;
	@Autowired
	private CityMapper cityMapper;

	@Override
	public Integer addInviteUser(InviteUserEntity inviteUserEntity) {
		return applyFlowDao.addInviteUser(inviteUserEntity);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateInviteUser(com.zaijiadd.app.applyflow.entity.InviteUserEntity)
	 */

	@Override
	public Integer updateInviteUser(InviteUserEntity inviteUserEntity) {
		applyFlowDao.updateInviteUser(inviteUserEntity);
		return null;
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUser(java.util.Map)
	 */

	@Override
	public InviteUserEntity queryInviteUser(Map<String, Object> param) {
		return applyFlowDao.queryInviteUser(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#addApplyStore(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer addApplyStore(ApplyStore applyStore) {
		Integer applyType = applyStore.getApplyType();

		Integer paymoneyType = applyStore.getPaymoneyType();// 付款类型
		if (ConstantStorePower.APPLY_PAYMONEY_NOTALL.equals(paymoneyType)) {// 定金直接给财务
			applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
		} else {// 全额
			Integer cityId = applyStore.getCityId();
			BigDecimal paidMoney = applyStore.getPaidMoney();// 已付金额
			BigDecimal needPaymoney = applyStore.getNeedPaymoney();// 应付金额
			BigDecimal personPaymoneyCount = paidMoney.add(needPaymoney);// 个人现在已经付的金额

			if (applyType.equals(ConstantStorePower.APPLY_TYPE_DEALERSHIP)) {// 经销权
				// 经销权价格计算
				Integer dealershipNum = applyStore.getDealershipNum();// 经销权个数
				BigDecimal dealershipNumBig = new BigDecimal(dealershipNum);
				BigDecimal cityDealershipMoney = new BigDecimal(1);
				CityDealership cityDealership = cityDealershipMapper.getCityMoneyByCityId(cityId);
				if (cityDealership != null) {
					cityDealershipMoney = cityDealership.getCityDealershipMoney();
				}
				BigDecimal needPaymoneyCount = new BigDecimal(0);
				if (dealershipNumBig.compareTo(BigDecimal.ZERO) == 0) {
					needPaymoneyCount = cityDealershipMoney;
				} else {
					needPaymoneyCount = cityDealershipMoney.multiply(dealershipNumBig);// 每个城市的价格X个数，需支付的
				}
				if (personPaymoneyCount.compareTo(needPaymoneyCount) < 0) {// 没有支付全额
					// 实际付的金额比应收的金额小，那么给主管审批
					applyStore.setWhoCheck(ConstantsRole.ROLE_MANAGERS);
				} else {// 实际付的金额比应收的金额 相等，给财务
					applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
				}
			} else if (applyType.equals(ConstantStorePower.APPLY_TYPE_SMALLSTORE)) {// 小店
				// 小店价格计算
				Integer storeNumm = applyStore.getStoreNumm();
				BigDecimal storeNummBig = new BigDecimal(storeNumm);
				BigDecimal needPaymoneyCount = ConstantStorePower.STORE_MONEY.multiply(storeNummBig);
				if (personPaymoneyCount.compareTo(needPaymoneyCount) < 0) {// 没有支付全额
					// 实际付的金额比应收的金额小，那么给主管审批
					applyStore.setWhoCheck(ConstantsRole.ROLE_MANAGERS);
				} else {// 实际付的金额比应收的金额 相等，给财务
					applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
				}
			}
		}

		return applyStoreDao.addApplyStore(applyStore);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryAllApplyStore(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryAllApplyStore(Map<String, Object> param) {
		return applyFlowDao.queryAllApplyStore(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserLike(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryInviteUserLike(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserLike(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserMap(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryInviteUserMap(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserMap(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateApplyStore(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer updateApplyStore(ApplyStore applyStore) {
		return applyStoreDao.updateApplyStore(applyStore);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryApplyStoreDetails(Integer)
	 */

	@Override
	public Map<String, Object> queryApplyStoreDetails(Integer applyStoreId) {
		return applyStoreDao.queryApplyStoreDetails(applyStoreId);
	}

	/**
	 * @return
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#getCityDealershipMoney(Integer)
	 */

	@Override
	public BigDecimal getCityDealershipMoney(Integer cityId) {
		ArrayList<City> city = cityMapper.selectCityByID(cityId);
		if (city.size() > 0) {
			return city.get(0).getCityMoney();

		}
		return new BigDecimal(1);

	}

	/**
	 * (用一句话描述方法的主要功能)
	 * @param applyUserRelation
	 */

	private void insertApplyRoleRelation(ApplyUserRelation applyUserRelation) {
		applyUserRelationDao.insertApplyRoleRelation(applyUserRelation);

	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryAllApplyStoreSate(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryAllApplyStoreSate(Map<String, Object> param) {
		return applyStoreDao.queryAllApplyStoreSate(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryFinanceApproveStoreTry(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryFinanceApproveStoreTry(Map<String, Object> param) {
		return applyStoreDao.queryFinanceApproveStoreTry(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryManagersApproveStoreTry(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryManagersApproveStoreTry(Map<String, Object> param) {
		return applyStoreDao.queryManagersApproveStoreTry(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#roleApproveStore(java.util.Map)
	 */

	@Override
	public Integer roleApproveStore(Map<String, Object> param) {
		Integer roleId = (Integer) param.get("roleId");
		Integer userId = (Integer) param.get("userId");
		Integer applyStoreId = (Integer) param.get("applyStoreId");
		Integer approveState = (Integer) param.get("approveState");
		this.queryApplyStoreDetails(applyStoreId);
		ApplyUserRelation applyUserRelation = new ApplyUserRelation();
		applyUserRelation.setApplyId(applyStoreId);
		applyUserRelation.setUserid(userId);// userId
		applyUserRelation.setRoleid(roleId);

		if (ConstantsRole.ROLE_FINANCE.equals(roleId)) {// 财务
			if (approveState == ConstantStorePower.approve_state_succ) {// 财务同意
				applyUserRelation.setApplyId(applyStoreId);
				applyUserRelation.setCaurApproveState(approveState);
				this.insertApplyRoleRelation(applyUserRelation);

				updateDealershipNum(applyStoreId);

				ApplyStore applyStore = new ApplyStore();
				applyStore.setApplyStoreId(applyStoreId);// id
				applyStore.setApplyStatus(ConstantStorePower.apply_state_succ);// 单子的状态
				applyStore.setFinanceCheck(ConstantStorePower.approve_state_succ);
				this.updateApplyStore(applyStore);
			}

		} else if (ConstantsRole.ROLE_MANAGERS.equals(roleId)) {// 主管
			applyUserRelation.setCaurApproveState(approveState);
			if (approveState == ConstantStorePower.approve_state_succ) {// 主管同意
				applyUserRelation.setApplyId(applyStoreId);
				applyUserRelation.setCaurApproveState(approveState);
				this.insertApplyRoleRelation(applyUserRelation);

				ApplyStore applyStore = new ApplyStore();
				applyStore.setApplyStoreId(applyStoreId);
				applyStore.setWhoCheck(ConstantsRole.ROLE_FINANCE);
				applyStore.setApplyStatus(ConstantStorePower.apply_state_ready);
				applyStore.setManagersCheck(ConstantStorePower.approve_state_succ);
				this.updateApplyStore(applyStore);
			} else if (approveState == ConstantStorePower.approve_state_fail) {// 主管拒绝
				applyUserRelation.setApplyId(applyStoreId);
				applyUserRelation.setCaurApproveState(approveState);
				this.insertApplyRoleRelation(applyUserRelation);

				ApplyStore applyStore = new ApplyStore();
				applyStore.setApplyStoreId(applyStoreId);
				applyStore.setApplyStatus(ConstantStorePower.apply_state_ready);
				applyStore.setManagersCheck(ConstantStorePower.approve_state_fail);
				this.updateApplyStore(applyStore);

			}
		}
		return null;
	}

	/**
	 * 更新城市的经销权个数
	 * @param applyStoreId
	 */

	void updateDealershipNum(Integer applyStoreId) {
		Map<String, Object> queryApplyStoreDetails = applyStoreDao.queryApplyStoreDetails(applyStoreId);
		Integer cityId = (Integer) queryApplyStoreDetails.get("cityId");// 用户的城市id
		Integer dealershipNum = (Integer) queryApplyStoreDetails.get("dealershipNum");// 用户的经销权个数
		if (dealershipNum != null && dealershipNum != 0) {
			CityDealership cityDealership = cityDealershipMapper.getCityMoneyByCityId(cityId);
			if (cityDealership != null) {
				Integer sellDealershipNum = cityDealership.getSellDealershipNum();// 城市经销权总的个数
				Integer dealershipNumAble = sellDealershipNum - dealershipNum;
				CityDealership cityDealership2 = new CityDealership(cityDealership.getCityDealershipId(), cityId,
						dealershipNumAble);
				cityDealershipMapper.updateCityDealership(cityDealership2);
			}
		}

	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryApproveMsg(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryApproveMsg(Map<String, Object> param) {
		return applyUserRelationDao.queryApproveMsg(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#printContract(Map)
	 */

	@Override
	public Map<String, Object> printContract(Map<String, Object> param) {
		return applyStoreDao.printContract(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserDet(java.util.Map)
	 */

	@Override
	public Map<String, Object> queryInviteUserDet(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserDet(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserMsgDet(java.util.Map)
	 */

	@Override
	public Map<String, Object> queryInviteUserMsgDet(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserMsgDet(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateInviteUserById(java.util.Map)
	 */

	@Override
	public Integer updateInviteUserById(Map<String, Object> param) {
		return applyFlowDao.updateInviteUserById(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryRoleApproveStoreTry(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryRoleApproveStoreTry(Map<String, Object> param) {
		return applyStoreDao.queryRoleApproveStoreTry(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryBankList(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryBankList(Map<String, Object> param) {
		return bankMapper.queryBankList(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateWhetherStartApply(com.zaijiadd.app.applyflow.entity.ApplyStore)
	 */

	@Override
	public Integer updateWhetherStartApply(ApplyStore applyStore) {
		return applyStoreDao.updateWhetherStartApply(applyStore);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryDealershipNumAble(java.lang.Integer)
	 */

	@Override
	public Map<String, Object> queryDealershipNumAble(Integer cityId) {
		return cityDealershipMapper.queryDealershipNumAble(cityId);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#updateUserAddFlagById(java.util.Map)
	 */

	@Override
	public Integer updateUserAddFlagById(Map<String, Object> param) {
		return applyFlowDao.updateUserAddFlagById(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryAllInviteUserMsg(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryAllInviteUserMsg(Map<String, Object> param) {
		return applyFlowDao.queryAllInviteUserMsg(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#queryInviteUserMsgLike(java.util.Map)
	 */

	@Override
	public List<Map<String, Object>> queryInviteUserMsgLike(Map<String, Object> param) {
		return applyFlowDao.queryInviteUserMsgLike(param);
	}

	/**
	 * @see com.zaijiadd.app.applyflow.service.ApplyFlowService#generateSerialNum()
	 */

	@Override
	public String generateSerialNum() {
		Random r = new Random();
		Double d = r.nextDouble();
		String s = d + "";
		return s = s.substring(3, 3 + 6);
	}

}
