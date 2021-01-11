package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageMeeting;

import java.util.List;

public interface ManageMeetingMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageMeeting record);

    int insertSelective(ManageMeeting record);

    ManageMeeting selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageMeeting record);

    int updateByPrimaryKey(ManageMeeting record);

    List<ManageMeeting> getList(ManageMeeting where);

    ManageMeeting selectById(String uuid);

    ManageMeeting selectAttendeesExtra(String uuid);

    int count();
}