package com.shanxi.coal.enums;

import java.util.HashMap;
import java.util.Map;

public enum AuditRoleEnum {

    MATERIAL_PERSONNEL_ENUM("1", "材料员", "MATERIAL_PERSONNEL"),
    TEAM_LEADER_ENUM("2", "队组组长", "TEAM_LEADER"),
    EQU_MANAGER_ENUM("3", "设备管理员", "EQU_MANAGER"),
    EQU_LEADER_ENUM("4", "设备组长", "EQU_LEADER"),
    EQU_JUNIOR_CHIEF_ENUM("5", "设备副科长", "EQU_JUNIOR_CHIEF"),
    EQU_CHIEF_ENUM("6", "设备科长", "EQU_CHIEF"),
    STORAGE_LEADER_ENUM("7", "库管组长", "STORAGE_LEADER"),
    STORAGE_MANAGER_ENUM("8", "库房管理员", "STORAGE_MANAGER"),
    ELE_JUNIOR_MINE_CHIEF_ENUM("9", "机电副矿长", "ELE_JUNIOR_MINE_CHIEF"),
    ELE_JUNIOR_CHIEF_ENUM("10", "机电副总", "ELE_JUNIOR_CHIEF"),
    LEASE_PROCESS_MANAGER_ENUM("11", "租赁管理员", "LEASE_PROCESS_MANAGER"),
    LEASE_EQU_MANAGER_ENUM("12", "租赁主机管理员", "LEASE_EQU_MANAGER"),
    LEASE_EQU_CHIEF_ENUM("13", "租赁设备科长", "LEASE_EQU_CHIEF"),
    EQU_MANAGER_CHECK("14", "设备管理员验收", "EQU_MANAGER_CHECK"),
    TEAM_LEADER_OUT_ENUM("15", "队组组长调出", "TEAM_LEADER_OUT"),
    ORG_EQU_MANAGER_ENUM("99", "焦煤集团公司设备管理员", "ORG_EQU_MANAGER_ENUM"),
    TEAM_LEADER_IN_ENUM("16", "队组组长接收", "TEAM_LEADER_IN"),
    OPERATION_MINE_CHIEF("17", "经营矿长", "OPERATION_MINE_CHIEF"),
    MINE_CHIEF("18", "矿长", "MINE_CHIEF"),
    LEASE_ASSISTANCE_MANAGER("19", "租赁公司副经理", "LEASE_ASSISTANCE_MANAGER"),
    FIX_EQU_MANAGER("20", "机修厂设备管理员", "FIX_EQU_MANAGER"),
    LEASE_HOLDS_CHIEF("21", "租赁库管科长", "LEASE_HOLDS_CHIEF"),
    LEASE_HOLDS_CLERK("22", "租赁库管科员", "LEASE_HOLDS_CLERK"),
    LEASE_DIRECTOR("23", "租赁主任", "LEASE_DIRECTOR"),
    LEASE_FINANCE_CHIEF("24", "租赁财务科长", "LEASE_FINANCE_CHIEF"),
    LEASE_GENERAL_OFFICE_CHIEF("25", "租赁综合办科长", "LEASE_GENERAL_OFFICE_CHIEF"),
    LEASE_LAW_OFFICER("26", "租赁法事办科长", "LEASE_LAW_OFFICER");

    private final String code;
    private final String msg;
    private final String detail;

    AuditRoleEnum(String code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public static String getCodeStrByMsg(String msg) {
        for (AuditRoleEnum myEnum : AuditRoleEnum.values()) {
            if (myEnum.getMsg().equals(msg)) {
                return myEnum.getCodeStr();
            }
        }
        return null;
    }
    public static String getMsgByCode(String code) {
        for (AuditRoleEnum myEnum : AuditRoleEnum.values()) {
            if (myEnum.getCode().equals(code)) {
                return myEnum.getMsg();
            }
        }
        return null;
    }
    public static String getCodeByDetail(String detail) {
        for (AuditRoleEnum myEnum : AuditRoleEnum.values()) {
            if (myEnum.getDetail().equals(detail)) {
                return myEnum.getCode();
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
        AuditRoleEnum[] production = AuditRoleEnum.values();
        for (AuditRoleEnum a : production){
            pro.put(a.getCode(),a.getMsg());
        }
        return pro;
    }
    public static Map<String,String> getDetailMap(){
        Map<String,String> pro = new HashMap<>();
        AuditRoleEnum[] production = AuditRoleEnum.values();
        for (AuditRoleEnum a : production){
            pro.put(a.getCode(),a.getDetail());
        }
        return pro;
    }

}
