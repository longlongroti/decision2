package com.shanxi.coal.controller;

import com.shanxi.coal.utils.MyUtils;
import liquibase.util.MD5Util;
import liquibase.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam(value = "captcha", required = false) String captcha,
                        HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtils.isEmpty(captcha) || request.getSession().getAttribute("captchaResult") == null || !request.getSession().getAttribute("captchaResult").toString().equals(captcha)) {
            model.addAttribute("error_invalid_captcha", true);
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            if (password.equals("1")) {
                return "redirect:/sysuser/gopwd";
            }
        }
        if (!subject.isAuthenticated()) {
            /*前台传入的用户名和密码*/
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, MD5Util.computeMD5(password));
            return userLogin(userName, password, response, model, subject, usernamePasswordToken);
        }
        return "redirect:/page/index";
    }

    @PostMapping("/loginm")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        return idmRequest(userName, password, response, model);
    }

    private String idmRequest(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletResponse response, Model model) {
        logger.info(userName);
        logger.info(password);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
            return userLogin(userName, password, response, model, subject, usernamePasswordToken);
        }
        return "redirect:/go/dashboard";
    }

    private String userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletResponse response, Model model, Subject subject, UsernamePasswordToken usernamePasswordToken) {
        try {
            /*登录认证*/
            subject.login(usernamePasswordToken);
            MyUtils.setCookie(response, userName);
            if (password.equals("1")) {
                return "redirect:/sysuser/gopwd";
            }
            SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 60);
            return "redirect:/page/index";
        } catch (UnknownAccountException e) {
            logger.info("Exception login ===" + e.toString());
            model.addAttribute("error_invalid_user", true);
            return "login";
        } catch (IncorrectCredentialsException e) {
            logger.info("Exception login ===" + e.toString());
            model.addAttribute("error_invalid_user", true);
            return "login";
        } catch (AuthenticationException e) {
            logger.info("Exception login ===" + e.toString());
            model.addAttribute("error_invalid_user", true);
            return "login";
        }
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random ran1 = new Random();
        Integer num1 = ran1.nextInt(10);
        Integer num2 = ran1.nextInt(10);
        BufferedImage bi = MyUtils.getCaptcha(num1, num2);
        request.getSession().setAttribute("captchaResult", num1 + num2);
        MyUtils.writeToStream(bi, "png", response.getOutputStream());
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        MyUtils.rmCookie(response);
        subject.logout();
        /*输出重定向，防止表单重复提交*/
        return "redirect:/page/login";
    }
}

