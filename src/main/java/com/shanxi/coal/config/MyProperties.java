package com.shanxi.coal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*将配置文件中的每一个属性值，映射到这个组件中
 * @ConfigurationProperties 告诉springboot将本类中的所有属性和配置文件中的相关的配置进行绑定
 *
 * 只有这个组件时容器中的组件，才能使用@ConfigurationProerties功能
 * 可以使用jsr303进行校验
 * 默认读取application.properties
 * */
@Component
/*
 * propertySource 指定读取的配置文件
 * */
@PropertySource(value = "classpath:my.properties")
@ConfigurationProperties(prefix = "my.prop")
public class MyProperties {

    private String reportUrl;
    private Map<String, List<String>> powerSpec = new HashMap<String, List<String>>();
    private Map<String,List<String>> initAuditUser = new HashMap<>();
    private Map<String,Map<String,String>> flow = new HashMap<>();
    private List<String> auditOrg = new ArrayList<>();
    private List<String> rentCompany = new ArrayList<>();

    public List<String> getRentCompany() {
        return rentCompany;
    }

    public void setRentCompany(List<String> rentCompany) {
        this.rentCompany = rentCompany;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public List<String> getAuditOrg() {
        return auditOrg;
    }

    public void setAuditOrg(List<String> auditOrg) {
        this.auditOrg = auditOrg;
    }

    public Map<String, Map<String, String>> getFlow() {
        return flow;
    }

    public void setFlow(Map<String, Map<String, String>> flow) {
        this.flow = flow;
    }

    public Map<String, List<String>> getInitAuditUser() {
        return initAuditUser;
    }

    public void setInitAuditUser(Map<String, List<String>> initAuditUser) {
        this.initAuditUser = initAuditUser;
    }

    public Map<String, List<String>> getPowerSpec() {
        return powerSpec;
    }

    public void setPowerSpec(Map<String, List<String>> powerSpec) {
        this.powerSpec = powerSpec;
    }


    private String fileUploadedPath;

    public String getFileUploadedPath() {
        return fileUploadedPath;
    }

    public void setFileUploadedPath(String fileUploadedPath) {
        this.fileUploadedPath = fileUploadedPath;
    }
}
