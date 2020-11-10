package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageLeaderGroup;

import java.util.List;

public interface ManageLeaderGroupMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageLeaderGroup record);

    int insertSelective(ManageLeaderGroup record);

    ManageLeaderGroup selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageLeaderGroup record);

    int updateByPrimaryKey(ManageLeaderGroup record);

    List<ManageLeaderGroup> listByUseId(String uuid);

    List<String> listByParentId(String uuid);
}