package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.enums.ReportEnum;
import com.shanxi.coal.utils.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Resource
    MyProperties myProperties;


    @GetMapping("/go")
    public String go(Model model, @RequestParam("type") String type, @RequestParam("redirect") Boolean redirect, @RequestParam(value = "url", required = false) Boolean url, HttpServletResponse response) {
        String codeStrByMsg = ReportEnum.getCodeStrByMsg(type);
        if (url == null)
            model.addAttribute("reportUrl", myProperties.getReportUrl() + codeStrByMsg + "&id=" + MyUtils.getSessionUser().getUuid()+ "&op=write");
        else if (url == true)
            model.addAttribute("reportUrl", codeStrByMsg);
        return redirect ? "report/rlist" : "report/list";
    }

}
