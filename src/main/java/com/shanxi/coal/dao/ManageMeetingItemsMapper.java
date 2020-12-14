package com.shanxi.coal.dao;

import com.shanxi.coal.domain.ManageMeetingItems;
import com.shanxi.coal.domain.ManageMeetingItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageMeetingItemsMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageMeetingItems record);

    int insertSelective(ManageMeetingItems record);

    ManageMeetingItems selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageMeetingItems record);

    int updateByPrimaryKey(ManageMeetingItems record);

    void deleteByMainId(String uuid);

    List<ManageMeetingItems> listByUseId(String uuid);

    List<ManageMeetingItems> listEquipIdByMain(String uuid);

    List<ManageMeetingItems> listByItemId(String uuid);

    List<ManageMeetingItems> listAllPending();

    List<ManageMeetingItems> listByParentId(String id);
}