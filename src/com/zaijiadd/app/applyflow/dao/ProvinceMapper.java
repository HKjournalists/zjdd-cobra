package com.zaijiadd.app.applyflow.dao;

import java.util.List;

import com.zaijiadd.app.applyflow.entity.Province;

public interface ProvinceMapper {
	
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);
    
    String selectNameById(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    
    List<Province> selectAll();
}