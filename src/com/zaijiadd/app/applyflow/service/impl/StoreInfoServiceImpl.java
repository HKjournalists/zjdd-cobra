package com.zaijiadd.app.applyflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
import com.zaijiadd.app.applyflow.dao.StoreImgDao;
import com.zaijiadd.app.applyflow.dao.StoreInfoDao;
import com.zaijiadd.app.applyflow.dao.TownMapper;
import com.zaijiadd.app.applyflow.dto.StoreApprovalDTO;
import com.zaijiadd.app.applyflow.dto.StoreInfoDTO;
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
	public int updateByPrimaryKeySelective(StoreInfo record) throws Exception {
		return this.storeInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public void applicationShop(String[] fileUrls, Long storeId) throws Exception {
		StoreInfo storeInfo = new StoreInfo();
		//图片审核中
		storeInfo.setStatus(2);
		storeInfo.setStoreId(storeId);
		//storeInfo.setApplicationShopTime(new );
		this.storeInfoDao.updateByPrimaryKeySelective(storeInfo);
		
		for(String fileUrl : fileUrls) {
			StoreImg storeImg = new StoreImg();
			storeImg.setImgUrl(fileUrl);
			storeImg.setStoreId(storeId);
			this.storeImgDao.insert(storeImg);
		}
	}

	@Override
	public List<StoreImg> selectImgsByStoreId(Long storeId) throws Exception {
		return this.storeImgDao.selectByStoreId(storeId);
	}

	@Override
	public List<Map<String, Object>> selectByApplicant(Map<String, Object> map) throws Exception {
		int type = Integer.parseInt(map.get("status").toString());
		switch(type) {
			case -1:
				break;
			case 0: //开户申请中
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 0);
			case 1://开户成功
				map.put("status", 1);
				map.put("addressAuditStatus", 1);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 0);
			case 2://开户失败
				map.put("status", 1);
				map.put("addressAuditStatus", -1);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 0);
			case 3://开店待申请
				map.put("status", 1);
				map.put("addressAuditStatus", 1);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 1);
			case 4: //开店申请中
				map.put("status", 2);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 2);
			case 5://开店审核成功
				map.put("status", 3);
				map.put("imgsAuditStatus", 1);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 2);
			case 6://开店审核失败
				map.put("status", 3);
				map.put("imgsAuditStatus", -1);
				return groupByDay(this.storeInfoDao.selectByApplicant(map), 2);
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
	public List<Map<String, Object>> getMyApproval(Map<String, Object> map) throws Exception {
		int type = Integer.parseInt(map.get("type").toString());
		//
		if(type == 0) {//地址待审批
			map.put("addressAuditStatus", 0);
			map.put("status", 0);
			return groupByApprovalDay(this.storeInfoDao.getMyApproval(map), 0);
		} else if(type == 1) { // 地址已审批
			//map.put("addressAuditStatus", 0);
			map.put("status", 1);
			map.put("addressApprover", map.get("userId"));
			return groupByApprovalDay(this.storeInfoDao.getMyApproval(map), 0);
		} else if(type == 2){ // 图片未审批
			map.put("status", 2);
			return groupByApprovalDay(this.storeInfoDao.getMyApproval(map), 1);
		} else {
			map.put("status", 3);
			map.put("imgsApprover", map.get("userId"));
			return groupByApprovalDay(this.storeInfoDao.getMyApproval(map), 1);
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

}
