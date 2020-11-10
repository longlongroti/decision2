package com.shanxi.coal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @GetMapping("/go")
    public String goPending(Model model) {
        return "dashboard/list";
    }

}
