package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSystemItems;

public interface ManageSystemItemsMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSystemItems record);

    int insertSelective(ManageSystemItems record);

    ManageSystemItems selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSystemItems record);

    int updateByPrimaryKey(ManageSystemItems record);
}