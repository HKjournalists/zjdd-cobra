package com.zaijiadd.app.applyflow.dao;

import java.util.List;
import java.util.Map;

import com.zaijiadd.app.applyflow.entity.Excel;

public interface ExcelMapper {
	
    int insert(Excel record);

    int insertSelective(Excel record);
    
    List<Excel> selectByName(Map<String, String> map);
}