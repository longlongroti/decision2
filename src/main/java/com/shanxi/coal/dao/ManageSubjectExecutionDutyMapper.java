package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSubjectExecutionDuty;

public interface ManageSubjectExecutionDutyMapper {
    int deleteByPrimaryKey(String uuid);

    int deleteByExecutionId(String executionId);

    int insert(ManageSubjectExecutionDuty record);

    int insertSelective(ManageSubjectExecutionDuty record);

    ManageSubjectExecutionDuty selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSubjectExecutionDuty record);

    int updateByPrimaryKey(ManageSubjectExecutionDuty record);
}