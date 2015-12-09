package com.zaijiadd.app.applyflow.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zaijiadd.app.applyflow.dao.ApplyStoreDao;
import com.zaijiadd.app.applyflow.dao.ApplyStoreDetailDao;
import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
import com.zaijiadd.app.applyflow.dao.ShopApplyMapper;
import com.zaijiadd.app.applyflow.dao.StoreImgDao;
import com.zaijiadd.app.applyflow.dao.StoreInfoDao;
import com.zaijiadd.app.applyflow.dao.TownMapper;
import com.zaijiadd.app.applyflow.dto.ShopVO;
import com.zaijiadd.app.applyflow.dto.StoreApprovalDTO;
import com.zaijiadd.app.applyflow.dto.StoreInfoDTO;
import com.zaijiadd.app.applyflow.entity.ApplyStoreDetail;
import com.zaijiadd.app.applyflow.entity.ShopApply;
import com.zaijiadd.app.applyflow.entity.StoreImg;
import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;
import com.zaijiadd.app.common.utils.DateUtils;

@Service
public class StoreInfoServiceImpl implements StoreInfoService {

	@Autowired
	private StoreInfoDao storeInfoDao;
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private TownMapper townMapper;
	@Autowired
	private StoreImgDao storeImgDao;
	@Autowired
	private ApplyStoreDao applyStoreDao;
	@Autowired
	private ShopApplyMapper shopApplyMapper;
	@Autowired
	ApplyStoreDetailDao applyStoreDetailDao;
	
	
	@Override
	public int deleteByPrimaryKey(Long storeId) throws Exception {
		return 0;
	}

	@Override
	public int insert(StoreInfo record) throws Exception {
		record.setStatus(0);
		record.setCapitalName(provinceMapper.selectNameById(record.getCapital()));
		record.setCityName(cityMapper.selectNameById(record.getCity()));
		record.setDistrictName(countryMapper.selectNameById(record.getDistrict()));
		record.setStreetName(townMapper.selectNameById(record.getStreet()));
		record.setIsHistory(0);
		ApplyStoreDetail applyStore = new ApplyStoreDetail();
		//applyStore.setApplyStoreId(record.getApplyStoreId().intValue());
		applyStore.setApplyStoreDetailId(record.getApplyStoreId().longValue());
		applyStore.setApplyStatus(3);
		applyStoreDetailDao.updateApplyStore(applyStore);
		return storeInfoDao.insert(record);
	}

	@Override
	public int insertSelective(StoreInfo record) throws Exception {
		return 0;
	}

	@Override
	public StoreInfo selectByPrimaryKey(Long storeId) throws Exception {
		return this.storeInfoDao.selectByPrimaryKey(storeId);
	}

	@Override
	public int updateByPrimaryKeySelective(StoreInfo record, ShopApply shopApply) throws Exception {
		if(shopApply != null) {
			this.shopApplyMapper.updateByPrimaryKeySelective(shopApply);
			ShopApply shop = this.shopApplyMapper.selectByPrimaryKey(shopApply.getShopId());
			record.setStoreId(shop.getStoreId());
		}
		return this.storeInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public void applicationShop(JSONArray fileUrls, Long storeId, Integer userId, String username, String password) throws Exception {
		StoreInfo storeInfo = new StoreInfo();
		//图片审核中
		storeInfo.setStatus(2);
		storeInfo.setStoreId(storeId);
		storeInfo.setApplicationShopTime(new Timestamp(new Date().getTime()));
		storeInfo.setShopApplicant(userId);
		this.storeInfoDao.updateByPrimaryKeySelective(storeInfo);
		
		ShopApply shopApply = new ShopApply();
		shopApply.setStoreId(storeId);
		shopApply.setApplicationShopTime(new Timestamp(new Date().getTime()));
		shopApply.setShopApplicant(userId);
		shopApply.setImgsAuditStatus(0);
		shopApply.setIsHistory(0);
		shopApply.setUsername(username);
		shopApply.setPassword(password);
		Long shopId = this.shopApplyMapper.insert(shopApply);
		for(Object fileUrl : fileUrls) {
			StoreImg storeImg = new StoreImg();
			storeImg.setImgUrl(fileUrl.toString());
			storeImg.setStoreId(shopApply.getShopId());
			this.storeImgDao.insert(storeImg);
		}
	}

	@Override
	public List<StoreImg> selectImgsByStoreId(Long storeId) throws Exception {
		return this.storeImgDao.selectByStoreId(storeId);
	}

	@Override
	public Map<String, Object> selectByApplicant(Map<String, Object> map) throws Exception {
		int type = Integer.parseInt(map.get("status").toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		switch(type) {
			case -1:
				map.put("yjsUserId", map.get("userId"));
				map.put("applyStatus", "1");
				
				resultMap.put("data", this.applyStoreDetailDao.queryAllApplyStoreSate(map));
				resultMap.put("total", this.applyStoreDetailDao.queryByParamCount(map));
				return resultMap;
			case 0: //开户申请中
				map.put("applicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantCount(map));
				return resultMap;
			case 1://开户成功
				map.put("status", 1);
				map.put("type", 1);
				map.put("addressAuditStatus", 1);
				map.put("applicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantCount(map));
				return resultMap;
			case 2://开户失败
				map.put("status", 1);
				map.put("addressAuditStatus", -1);
				map.put("applicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantCount(map));
				return resultMap;
			case 3://开店待申请
				map.put("status", 1);
				map.put("addressAuditStatus", 1);
				map.put("applicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantCount(map));
				return resultMap;
			case 4: //开店申请中
				map.put("status", 2);
				map.put("shopApplicant", map.get("userId"));
				map.remove("applicant");
				resultMap.put("data", this.storeInfoDao.selectShopByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantShopCount(map));
				return resultMap;
			case 5://开店审核成功
				map.put("status", 3);
				map.put("imgsAuditStatus", 1);
				map.put("shopApplicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectShopByApplicant(map));
				resultMap.put("total", this.storeInfoDao.selectShopByApplicant(map));
				return resultMap;
			case 6://开店审核失败
				map.put("status", 3);
				map.put("imgsAuditStatus", -1);
				map.put("shopApplicant", map.get("userId"));
				resultMap.put("data", this.storeInfoDao.selectShopByApplicant(map));
				resultMap.put("total", this.storeInfoDao.applicantShopCount(map));
				return resultMap;
				default:
					break;
		}
		return null;
	}

	
	
	private List<Map<String, Object>> groupByDay(List<StoreInfoDTO> list, int type) {
		List<Map<String, Object>> returnList = new ArrayList<>();
		HashSet<String> daySet = new HashSet<>();
		if(list != null) {
			for(StoreInfoDTO dto : list) {
				String  day = null;
				if(type == 0) {
					day = DateUtils.transDateToString(new Date(dto.getApplicationTime().getTime()), DateUtils.SHORT_DATE_TIME);
				} else if(type == 1) {
					day = DateUtils.transDateToString(new Date(dto.getAddressApprovalTime().getTime()), DateUtils.SHORT_DATE_TIME);
				} else {
					day = DateUtils.transDateToString(new Date(dto.getApplicationShopTime().getTime()), DateUtils.SHORT_DATE_TIME);
				}
				daySet.add(day);
			}
			
			Iterator<String>  it = daySet.iterator();
			while(it.hasNext()) {
				Map<String, Object> returnMap  = new HashMap<String, Object>();
				String day = it.next();
				List<StoreInfoDTO> dayList = new ArrayList<>();
				for(StoreInfoDTO dto : list) {
					String otherDay = null;
					if(type == 0) {
						otherDay = DateUtils.transDateToString(new Date(dto.getApplicationTime().getTime()), DateUtils.SHORT_DATE_TIME);
					} else if(type == 1) {
						otherDay = DateUtils.transDateToString(new Date(dto.getAddressApprovalTime().getTime()), DateUtils.SHORT_DATE_TIME);
					} else {
						otherDay = DateUtils.transDateToString(new Date(dto.getApplicationShopTime().getTime()), DateUtils.SHORT_DATE_TIME);
					}
					if(day.equals(otherDay)) {
						dayList.add(dto);
					}
				}
				returnMap.put(day, dayList);
				returnList.add(returnMap);
			}
			
		}
		return returnList;
	}
	
	@Override
	public Map<String, Object> getMyApproval(Map<String, Object> map) throws Exception {
		int type = Integer.parseInt(map.get("type").toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//
		if(type == 0) {//地址待审批
			map.put("addressAuditStatus", 0);
			map.put("status", 0);
			resultMap.put("data", this.storeInfoDao.getMyApproval(map));
			resultMap.put("total", this.storeInfoDao.approvalCount(map));
			return resultMap;
		} else if(type == 1) { // 地址已审批
			//map.put("addressAuditStatus", 0);
			map.put("status", 1);
			map.put("addressApprover", map.get("userId"));
			resultMap.put("data", this.storeInfoDao.getMyApproval(map));
			resultMap.put("total", this.storeInfoDao.approvalCount(map));
			return resultMap;
		} else if(type == 2){ // 图片未审批
			//map.put("status", 2);
			map.put("imgsAuditStatus", 0);
			resultMap.put("data", this.storeInfoDao.getShopApproval(map));
			resultMap.put("total", this.storeInfoDao.approvalShopCount(map));
			return resultMap;
		} else {
			//map.put("status", 3);
			map.put("imgsApprover", map.get("userId"));
			map.put("imgsAuditStatus", 1);
			resultMap.put("data", this.storeInfoDao.getShopApproval(map));
			resultMap.put("total", this.storeInfoDao.approvalShopCount(map));
			return resultMap;
		}
		
	}
	
	private List<Map<String, Object>> groupByApprovalDay(List<StoreApprovalDTO> list, int type) {
		List<Map<String, Object>> returnList = new ArrayList<>();
		HashSet<String> daySet = new HashSet<>();
		if(list != null) {
			for(StoreApprovalDTO dto : list) {
				String  day = null;
				if(type == 0) {
					day = DateUtils.transDateToString(new Date(dto.getApplicationTime().getTime()), DateUtils.SHORT_DATE_TIME);
				} else {
					day = DateUtils.transDateToString(new Date(dto.getApplicationShopTime().getTime()), DateUtils.SHORT_DATE_TIME);
				}
				daySet.add(day);
			}
			
			Iterator<String>  it = daySet.iterator();
			while(it.hasNext()) {
				Map<String, Object> returnMap  = new HashMap<String, Object>();
				String day = it.next();
				List<StoreApprovalDTO> dayList = new ArrayList<>();
				for(StoreApprovalDTO dto : list) {
					String otherDay = null;
					if(type == 0) {
						otherDay = DateUtils.transDateToString(new Date(dto.getApplicationTime().getTime()), DateUtils.SHORT_DATE_TIME);
					}else {
						otherDay = DateUtils.transDateToString(new Date(dto.getApplicationShopTime().getTime()), DateUtils.SHORT_DATE_TIME);
					}
					if(day.equals(otherDay)) {
						dayList.add(dto);
					}
				}
				returnMap.put(day, dayList);
				returnList.add(returnMap);
			}
			
		}
		return returnList;
	}

	@Override
	public int reApply(StoreInfo record) throws Exception {
		record.setStatus(0);
		record.setCapitalName(provinceMapper.selectNameById(record.getCapital()));
		record.setCityName(cityMapper.selectNameById(record.getCity()));
		record.setDistrictName(countryMapper.selectNameById(record.getDistrict()));
		record.setStreetName(townMapper.selectNameById(record.getStreet()));
		record.setIsHistory(0);
		//上一个申请成为历史记录
		StoreInfo hisInfo = this.storeInfoDao.selectByPrimaryKey(record.getStoreId());
		hisInfo.setIsHistory(1);
		this.storeInfoDao.updateByPrimaryKeySelective(hisInfo);
		record.setStoreId(null);
		return storeInfoDao.insert(record);
	}

	@Override
	public void ReApplicationShop(JSONArray fileUrls, Long storeId, Integer userId, String username, String password) throws Exception {
		ShopApply hisApply = this.shopApplyMapper.selectByPrimaryKey(storeId);
		
		StoreInfo storeInfo = new StoreInfo();
		//图片审核中
		storeInfo.setStatus(2);
		storeInfo.setStoreId(hisApply.getStoreId());
		storeInfo.setApplicationShopTime(new Timestamp(new Date().getTime()));
		storeInfo.setShopApplicant(userId);
		this.storeInfoDao.updateByPrimaryKeySelective(storeInfo);
		
		hisApply.setIsHistory(1);
		this.shopApplyMapper.updateByPrimaryKeySelective(hisApply);
		ShopApply shopApply = new ShopApply();
		shopApply.setStoreId(hisApply.getStoreId());
		shopApply.setApplicationShopTime(new Timestamp(new Date().getTime()));
		shopApply.setShopApplicant(userId);
		shopApply.setImgsAuditStatus(0);
		shopApply.setIsHistory(0);
		shopApply.setUsername(username);
		shopApply.setPassword(password);
		Long shopId = this.shopApplyMapper.insert(shopApply);
		for(Object fileUrl : fileUrls) {
			StoreImg storeImg = new StoreImg();
			storeImg.setImgUrl(fileUrl.toString());
			storeImg.setStoreId(shopApply.getShopId());
			this.storeImgDao.insert(storeImg);
		}
	}

	@Override
	public ShopVO selectByShopId(Long shopId) throws Exception {
		return this.shopApplyMapper.selectByShopId(shopId);
	}

}
