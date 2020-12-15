package com.shanxi.coal.dao;

import com.shanxi.coal.domain.DicEventsCatalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicEventsCatalogMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DicEventsCatalog record);

    int insertSelective(DicEventsCatalog record);

    DicEventsCatalog selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(DicEventsCatalog record);

    int updateByPrimaryKey(DicEventsCatalog record);

    List<DicEventsCatalog> getEventsList();

    List<DicEventsCatalog> listInIds(@Param("list") String[] s);

    List<DicEventsCatalog> listAll();

    List<DicEventsCatalog> getEventsListA();

    List<DicEventsCatalog> getEventsListB(String catalogA);

    List<DicEventsCatalog> getEventsListC(String catalogB);
}