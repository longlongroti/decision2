package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSubjectItem;

import java.util.List;

public interface ManageSubjectItemMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSubjectItem record);

    int insertSelective(ManageSubjectItem record);

    ManageSubjectItem selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSubjectItem record);

    int updateByPrimaryKey(ManageSubjectItem record);

    List<ManageSubjectItem> listBySubjectId(String subjectId);
}