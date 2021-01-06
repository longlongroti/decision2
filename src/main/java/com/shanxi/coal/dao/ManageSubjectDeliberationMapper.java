package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageSubjectDeliberation;

public interface ManageSubjectDeliberationMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageSubjectDeliberation record);

    int insertSelective(ManageSubjectDeliberation record);

    ManageSubjectDeliberation selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageSubjectDeliberation record);

    int updateByPrimaryKey(ManageSubjectDeliberation record);
}