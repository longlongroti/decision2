package com.shanxi.coal.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportEnum {

    JM_LD_CXYXZHFX("YDD_ZSBB/JM_LD_CXYXZHFX.frm", "report1","山西焦煤产洗运销综合分析"),
    JM_LD_YMCLCBFX("YDD_ZSBB/JM_LD_YMCLCBFX.frm", "report2","山西焦煤原煤综合分析"),
    JM_LD_XMLCLCBFX("YDD_ZSBB/JM_LD_XMLCLCBFX.frm", "report3","山西焦煤洗煤厂综合分析"),
    XSZHFX("YDD_ZSBB/xszhfx.frm", "report4","山西焦煤铁路运量分析"),
    PC_QYYLZS("YDD_ZSBB/pc_qyylzs.frm", "report5","区域运量走势分析"),
    PC_MZQYYL("YDD_ZSBB/JM_LD_YKYK.frm", "report6","一矿一卡"),
    PC_SCDWXSJG("YDD_ZSBB/pc_scdwxsjg.frm", "report7","生产单位冶炼精煤销售结构"),
    JM_LD_CXZBZHFX("YDD_ZSBB/JM_LD_CXZBZHFX.frm", "report8","焦煤产销指标完成分析"),
    JM_LD_MTHKZHFX("YDD_ZSBB/JM_LD_MTHKZHFX.frm", "report9","焦煤煤炭贷款回收分析"),
    JM_LD_HYSRLR("YDD_ZSBB/JM_LD_HYSRLR.frm", "report10","焦煤分行业收入分析"),
    JM_LD_ZGGSRFX("YDD_ZSBB/JM_LD_ZGGSRFX.frm", "report11","焦煤集团收入利润分析"),
    JM_LD_JMCL("YDD_ZSBB/JM_LD_JMCL.frm", "report12","山西焦煤精煤产率分析"),
    JM_LD_CXKHFX("YDD_ZSBB/JM_LD_CXKHFX.frm", "report13","山西焦煤客户运量分析"),
    JM_LD_ZGS_YLFX("YDD_ZSBB/JM_LD_ZGS_YLFX.frm", "report14","山西焦煤各矿运量分析"),
    USAGE_RATE("usage_rate.frm", "report15","山西焦煤设备使用率统计表"),
    report16("equipment_opening_rate.frm", "report16","设备开机率总览"),
    report17("YDD_ZSBB/JM_LD_YMZGXCFX.frm", "report17","煤矿职工薪酬分析"),
    report18("YDD_ZSBB/JM_LD_XMLZGXCFX.frm", "report18","洗煤厂职工薪酬分析"),
    report19("YDD_ZSBB/JM_LD_CXYX_ZL.frm", "report19","产洗运销总览"),

    report9001("plan_fill_up/gdzctzzyzbjhwcqk.cpt", "report9001","固定资产投资主要指标计划完成情况"),
    report9002("plan_fill_up/gdzctzalbwcqk.cpt", "report9002","固定资产投资按类别分完成情况"),
    report9003("plan_fill_up/gdwglzjb.cpt", "report9003","各类资金完成情况"),
    report9004("plan_fill_up/sxjmjtgsntzjhhzb_show.cpt", "report9004","投资计划汇总表"),
    report9005("plan_fill_up/sxjmjtgsndgdzcgqtzjjcxmmxb_show.cpt", "report9005","年度固定资产投资计划项目明细表"),
    report9006("plan_fill_up/aqfy.cpt", "report9006","安全费用"),

    report6001("HB_ZHFX.frm", "report6001","环保平台综合分析"),
    report6002("HB_ZXJC.frm", "report6002","环保在线监测"),
    report6003("clean_bills.frm", "report6003","三个责任清单"),

    report3001("HR_RYJG.frm", "report3001","人员构成分析"),
    report3002("HR_RYDD.frm", "report3002","人员调动分析"),
    report3004("HR_RYJY.frm", "report3004","人员减员分析"),
    report3003("HR_SZDW.frm", "report3003","三支队伍、三条线人员结构分析"),

    report4001("devices_show.frm", "report4001","集团设备总览"),
    report4002("devicesInformationCollect.frm", "report4002","各矿设备信息汇总"),
    report4003("category_infomation.frm", "report4003","按设备种类实时查询"),
    report4004("failure_rate.frm", "report4004","山西焦煤设备故障率统计表"),
    report4005("complete_equipment.frm", "report4005","成套综采设备情况"),

    report2001("XS_ZTFX.frm", "report2001","整体分析"),
    report2002("XS_YKYD.frm", "report2002","一矿一档"),
    report2003("http://172.16.65.9:8080/WebReport/ReportServer?formlet=%E5%B1%B1%E8%A5%BF%E7%84%A6%E7%85%A4%E5%85%AC%E8%B7%AF%E8%BF%90%E8%BE%93.frm",
            "report2003","山西焦煤公路运输总览"),
    report2004("http://172.16.65.9:8080/WebReport/ReportServer?formlet=%E7%85%A4%E7%82%AD%E4%BB%B7%E6%A0%BC%E9%94%80%E9%87%8F%E8%B5%B0%E5%8A%BF.frm",
            "report2004","公路煤价格销量走势"),
    report2005(" http://172.16.65.9:8080/WebReport/ReportServer?formlet=%E4%B8%8A%E6%8A%A5%E6%83%85%E5%86%B5%E6%95%B4%E4%BD%93%E5%88%86%E6%9E%90.frm",
            "report2005","公路销售上报情况展示"),
    report2006("http://172.16.65.9:8080/WebReport/ReportServer?formlet=%E8%BF%90%E8%BE%93%E6%83%85%E5%86%B5%E5%B1%95%E7%A4%BA.frm",
            "report2006","公路运输情况分析"),
    report2007("http://172.16.65.9:8080/WebReport/ReportServer?formlet=%E5%BA%93%E5%AD%98%E6%83%85%E5%86%B5%E5%B1%95%E7%A4%BA.frm",
            "report2007","公路煤库存情况展示");

    private final String code;
    private final String msg;
    private final String detail;

    ReportEnum(String code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public static String getCodeStrByMsg(String msg) {
        for (ReportEnum myEnum : ReportEnum.values()) {
            if (myEnum.getMsg().equals(msg)) {
                return myEnum.getCodeStr();
            }
        }
        return null;
    }

    public static String getMsgByCode(String code) {
        for (ReportEnum myEnum : ReportEnum.values()) {
            if (myEnum.getCode().equals(code)) {
                return myEnum.getMsg();
            }
        }
        return null;
    }

    public static Map<String, String> getMap() {
        Map<String, String> pro = new HashMap<>();
        ReportEnum[] production = ReportEnum.values();
        for (ReportEnum a : production) {
            pro.put(a.getCode(), a.getMsg());
        }
        return pro;
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

    public String getDetail() {
        return detail;
    }
}
