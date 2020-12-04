package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageEventsList;

import java.util.List;

public interface ManageEventsListMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageEventsList record);

    int insertSelective(ManageEventsList record);

    ManageEventsList selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageEventsList record);

    int updateByPrimaryKey(ManageEventsList record);

    List<ManageEventsList> getList(ManageEventsList where);

    ManageEventsList selectById(String uuid);
}