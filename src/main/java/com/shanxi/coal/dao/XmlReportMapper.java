package com.shanxi.coal.dao;

import com.shanxi.coal.domain.XmlReport;

public interface XmlReportMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(XmlReport record);

    int insertSelective(XmlReport record);

    XmlReport selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(XmlReport record);

    int updateByPrimaryKey(XmlReport record);
}