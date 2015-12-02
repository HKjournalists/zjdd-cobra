package com.zaijiadd.app.applyflow.service;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.dto.StoreInfoDTO;
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

	  int insertSelective(StoreInfo record) throws Exception;

	  StoreInfo selectByPrimaryKey(Long storeId) throws Exception;

	  int updateByPrimaryKeySelective(StoreInfo record) throws Exception;
	  
	  void applicationShop(String[] fileUrls, Long storeId) throws Exception;
	  
	  List<StoreImg> selectImgsByStoreId(Long storeId) throws Exception;
	  
	  /**
	   * 我的申请
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  List<Map<String, Object>> selectByApplicant(Map<String, Object> map) throws Exception;
	  /**
	   * 我的申请列表
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  List<Map<String, Object>> getMyApproval(Map<String, Object> map) throws Exception;

}
