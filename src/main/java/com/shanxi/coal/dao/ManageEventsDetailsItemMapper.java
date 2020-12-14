package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageEventsDetailItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageEventsDetailsItemMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageEventsDetailItem record);

    int insertSelective(ManageEventsDetailItem record);

    ManageEventsDetailItem selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageEventsDetailItem record);

    int updateByPrimaryKey(ManageEventsDetailItem record);

    void deleteByMainId(String uuid);

    List<ManageEventsDetailItem> listByParentId(@Param("uuid") String uuid);

    List<ManageEventsDetailItem> listEquipByMainId(String uuid);

    List<ManageEventsDetailItem> listByUseId(String uuid);

    List<ManageEventsDetailItem> listEquipIdByMain(String uuid);

    List<ManageEventsDetailItem> listByItemId(String uuid);

    List<ManageEventsDetailItem> listAllPending();
}