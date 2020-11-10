package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.SysLogMapper;
import com.shanxi.coal.domain.SysLog;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Resource
    SysLogMapper sysLogMapper;

    @GetMapping("/go")
    public String go() {
        return "system/log/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize,
                       @RequestParam(value = "name", required = false) String name) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        SysLog where = new SysLog();
        where.setUserName(StringUtils.isNotEmpty(name)?name:null);
        List<SysLog> sysLogs = sysLogMapper.list(where);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(sysLogs);
        return MyUtils.pageInfoToJson(pageInfo);
    }

}
