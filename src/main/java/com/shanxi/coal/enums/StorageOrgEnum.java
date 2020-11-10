package com.shanxi.coal.enums;

import java.util.HashMap;
import java.util.Map;

public enum StorageOrgEnum {

    IS_STORAGE_ENUM("1", "是", "isStorageOrg"),
    IS_NOT_STORAGE_ENUM("0", "否", "isNotStorageOrg");

    private final String code;
    private final String msg;
    private final String detail;

    StorageOrgEnum(String code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public static String getCodeStrByMsg(String msg) {
        for (StorageOrgEnum myEnum : StorageOrgEnum.values()) {
            if (myEnum.getMsg().equals(msg)) {
                return myEnum.getCodeStr();
            }
        }
        return null;
    }

    public String getDetail() {
        return detail;
    }

    public String getCode() {
        return code;
    }

    public String getCodeStr() {
        return String.valueOf(code);
    }

    public String getMsg() {
        return msg;
    }

    public static Map<String,String> getMap(){
        Map<String,String> pro = new HashMap<>();
        StorageOrgEnum[] production = StorageOrgEnum.values();
        for (StorageOrgEnum a : production){
            pro.put(a.getCode()+"",a.getMsg());
        }
        return pro;
    }

}
