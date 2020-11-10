package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageLeader;

import java.util.List;

public interface ManageLeaderMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageLeader record);

    int insertSelective(ManageLeader record);

    ManageLeader selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageLeader record);

    int updateByPrimaryKey(ManageLeader record);

    List<ManageLeader> getList(ManageLeader manageLeader);

    ManageLeader selectById(String uuid);
}