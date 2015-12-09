package com.zaijiadd.app.applyflow.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.zaijiadd.app.applyflow.dto.ShopVO;
import com.zaijiadd.app.applyflow.entity.ShopApply;
import com.zaijiadd.app.applyflow.entity.StoreImg;
import com.zaijiadd.app.applyflow.entity.StoreInfo;

/**
 * 
 * @author guofeng
 *
 */
public interface StoreInfoService {
	
	  int deleteByPrimaryKey(Long storeId) throws Exception;

	  int insert(StoreInfo record) throws Exception;
	  
	  /**
	   * 重新申请开户
	   * @param record
	   * @return
	   * @throws Exception
	   */
	  int reApply(StoreInfo record) throws Exception;

	  int insertSelective(StoreInfo record) throws Exception;

	  StoreInfo selectByPrimaryKey(Long storeId) throws Exception;

	  int updateByPrimaryKeySelective(StoreInfo record, ShopApply shopApply) throws Exception;
	  
	  void applicationShop(JSONArray fileUrls, Long storeId, Integer userId, String username, String password) throws Exception;
	  
	  void ReApplicationShop(JSONArray fileUrls, Long storeId, Integer userId, String username, String password) throws Exception;
	  
	  List<StoreImg> selectImgsByStoreId(Long storeId) throws Exception;
	  
	  /**
	   * 我的申请
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  Map<String, Object> selectByApplicant(Map<String, Object> map) throws Exception;
	  /**
	   * 我的申请列表
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  Map<String, Object> getMyApproval(Map<String, Object> map) throws Exception;
	  
	  ShopVO selectByShopId(Long shopId)throws Exception;

}
