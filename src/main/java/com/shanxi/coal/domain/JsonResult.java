package com.shanxi.coal.domain;

import com.google.gson.Gson;

public class JsonResult {

    private String appid;
    private String code;
    private String desc;
    private String redirect;

    public JsonResult(String appid, String code, String desc, String redirect) {
        this.appid = appid;
        this.code = code;
        this.desc = desc;
        this.redirect = redirect;
    }

    public static String errorMsg(String msg) {
        Gson gson = new Gson();
        return gson.toJson(new JsonResult("szyd", "E", msg, ""));
    }

    public static String okMsg(String code) {
        Gson gson = new Gson();
        return gson.toJson(new JsonResult("szyd", "S", "", "user/loginm?code=" + code));
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
