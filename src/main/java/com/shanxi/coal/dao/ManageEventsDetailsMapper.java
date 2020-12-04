package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageEventsDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageEventsDetailsMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageEventsDetails record);

    int insertSelective(ManageEventsDetails record);

    ManageEventsDetails selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageEventsDetails record);

    int updateByPrimaryKey(ManageEventsDetails record);

    void deleteByMainId(String uuid);

    List<ManageEventsDetails> listByParentId(@Param("uuid")String uuid);

    List<ManageEventsDetails> listEquipByMainId(String uuid);

    List<ManageEventsDetails> listByUseId(String uuid);

    List<ManageEventsDetails> listEquipIdByMain(String uuid);
}