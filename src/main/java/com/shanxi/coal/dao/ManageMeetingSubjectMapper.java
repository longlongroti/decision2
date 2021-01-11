package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageMeetingSubject;

import java.util.List;

public interface ManageMeetingSubjectMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageMeetingSubject record);

    int insertSelective(ManageMeetingSubject record);

    ManageMeetingSubject selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageMeetingSubject record);

    int updateByPrimaryKey(ManageMeetingSubject record);

    List<ManageMeetingSubject> listByMeetingId(String meetingId);

    ManageMeetingSubject selectById(String uuid);

    int count();
}