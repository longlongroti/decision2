package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSubjectAttendance;

import java.util.List;

public interface ManageSubjectAttendanceMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSubjectAttendance record);

    int insertSelective(ManageSubjectAttendance record);

    ManageSubjectAttendance selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSubjectAttendance record);

    int updateByPrimaryKey(ManageSubjectAttendance record);

    List<ManageSubjectAttendance> listBySubjectId(String subjectId);

    int deleteBySubjectId(String subjectId);
}