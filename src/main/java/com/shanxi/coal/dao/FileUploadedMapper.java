package com.shanxi.coal.dao;

import com.shanxi.coal.domain.FileUploaded;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileUploadedMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(FileUploaded record);

    int insertSelective(FileUploaded record);

    FileUploaded selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(FileUploaded record);

    int updateByPrimaryKey(FileUploaded record);

    List<FileUploaded> listFile(FileUploaded where);

    List<FileUploaded> listByCategoryId(@Param("fileCategoryId") String fileCategoryId);
    List<FileUploaded> listContractFile(@Param("fileCategoryId") String fileCategoryId);
    List<FileUploaded> listContractSignature(@Param("fileCategoryId") String fileCategoryId);
    List<FileUploaded> listContractQualification(@Param("fileCategoryId") String fileCategoryId);
    List<FileUploaded> listContractAuth(@Param("fileCategoryId") String fileCategoryId);
    List<FileUploaded> listContractTech(@Param("fileCategoryId") String fileCategoryId);

    List<FileUploaded> listRemarkedByCategoryId(@Param("fileCategoryId") String fileCategoryId);

    FileUploaded listSignature(@Param("fileCategoryId") String fileCategoryId);

    void updateSignatureBy(@Param("fileCategory") String toString, @Param("fileCategoryId") String uuid);
}
