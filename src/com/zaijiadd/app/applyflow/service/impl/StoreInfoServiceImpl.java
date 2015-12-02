package com.zaijiadd.app.applyflow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaijiadd.app.applyflow.dao.CityMapper;
import com.zaijiadd.app.applyflow.dao.CountryMapper;
import com.zaijiadd.app.applyflow.dao.ProvinceMapper;
import com.zaijiadd.app.applyflow.dao.StoreImgDao;
import com.zaijiadd.app.applyflow.dao.StoreInfoDao;
import com.zaijiadd.app.applyflow.dao.TownMapper;
import com.zaijiadd.app.applyflow.entity.StoreImg;
import com.zaijiadd.app.applyflow.entity.StoreInfo;
import com.zaijiadd.app.applyflow.service.StoreInfoService;

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

}
