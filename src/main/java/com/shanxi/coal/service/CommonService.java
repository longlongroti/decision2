package com.shanxi.coal.service;

import com.shanxi.coal.dao.FileUploadedMapper;
import com.shanxi.coal.domain.FileUploaded;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonService {
    @Resource
    FileUploadedMapper fileUploadedMapper;

    public   void  batchUpdateFileId(String ids,String categoryId){
        String arr[] = ids.split(",");
        for(String i : arr){
            FileUploaded fileUploaded = fileUploadedMapper.selectByPrimaryKey(i);
            if(fileUploaded!=null){
                fileUploaded.setFileCategoryId(categoryId);
                fileUploadedMapper.updateByPrimaryKeySelective(fileUploaded);
            }
        }
    }
}
