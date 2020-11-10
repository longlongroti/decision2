package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrgMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(String uuid);

    String selectOrgNameById(String uuid);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);

    SysOrg findRoot();

    List<SysOrg> listOrgByParent(@Param("uuid") String uuid);

    List<SysOrg> listAll();

    List<SysOrg> listStorageOrg();
    List<SysOrg> listRentOrg();
    List<SysOrg> listTwoLevelOrg();

    List<SysOrg> listOrgByUuid(String uuid);

    List<SysOrg> listStorageOrgLikeUuid(String companyId);

    List<SysOrg> listStorageOrgById(String storageId);

    List<SysOrg> listRealStorageOrg();
}
