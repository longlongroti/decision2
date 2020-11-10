package com.shanxi.coal.enums;


public enum MyDelEnum {

    NORMAL_STAUTS(0, "normal", "正常"),
    DELETED_STATUS(1, "deleted", "删除");

    private final Integer code;
    private final String msg;
    private final String detail;

    MyDelEnum(Integer code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public static String getCodeStrByMsg(String msg) {
        for (MyDelEnum myEnum : MyDelEnum.values()) {
            if (myEnum.getMsg().equals(msg)) {
                return myEnum.getCodeStr();
            }
        }
        return null;
    }

    public String getDetail() {
        return detail;
    }

    public Integer getCode() {
        return code;
    }

    public String getCodeStr() {
        return String.valueOf(code);
    }

    public String getMsg() {
        return msg;
    }

}
