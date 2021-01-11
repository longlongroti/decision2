package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSystem;

import java.util.List;

public interface ManageSystemMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSystem record);

    int insertSelective(ManageSystem record);

    ManageSystem selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSystem record);

    int updateByPrimaryKey(ManageSystem record);

    List<ManageSystem> getList(ManageSystem where);

    ManageSystem selectById(String uuid);

    Integer count();
}