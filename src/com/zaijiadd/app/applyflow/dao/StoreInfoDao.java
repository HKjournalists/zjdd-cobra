package com.zaijiadd.app.applyflow.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zaijiadd.app.applyflow.dto.StoreApprovalDTO;
import com.zaijiadd.app.applyflow.dto.StoreInfoDTO;
import com.zaijiadd.app.applyflow.entity.StoreInfo;

@Repository
public interface StoreInfoDao {
	
    int deleteByPrimaryKey(Long storeId);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    StoreInfo selectByPrimaryKey(Long storeId);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
    
    List<StoreInfoDTO> selectByApplicant(Map<String, Object> map);
    
    List<StoreApprovalDTO> getMyApproval(Map<String, Object> map);
    
    int applicantCount(Map<String, Object> map);
    
    int approvalCount(Map<String, Object> map);
}