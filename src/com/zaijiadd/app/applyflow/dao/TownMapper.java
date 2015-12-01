package com.zaijiadd.app.applyflow.dao;

import com.zaijiadd.app.applyflow.entity.Town;

public interface TownMapper {
    int deleteByPrimaryKey(Integer townId);

    int insert(Town record);

    int insertSelective(Town record);

    Town selectByPrimaryKey(Integer townId);

    int updateByPrimaryKeySelective(Town record);

    int updateByPrimaryKey(Town record);
}