package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSubjectExecution;

import java.util.List;

public interface ManageSubjectExecutionMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSubjectExecution record);

    int insertSelective(ManageSubjectExecution record);

    ManageSubjectExecution selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSubjectExecution record);

    int updateByPrimaryKey(ManageSubjectExecution record);

    List<ManageSubjectExecution> listBySubjectId(String subjectId);
}