package com.shanxi.coal.controller;

import com.shanxi.coal.utils.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/index")
    public String go3(Model model) {
        model.addAttribute("sessionUser", MyUtils.getSessionUser());
        return "index";
    }

    @GetMapping("/login")
    public String go2() {
        return "login";
    }


}
