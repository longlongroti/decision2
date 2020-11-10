package com.shanxi.coal.config;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/*默认springboot 使用的是AcceptHeaderLocaleResolver*/
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("lang");
        Locale locale = Locale.getDefault();
        if (obj != null) {
            String[] arr = obj.toString().split("_");
            if (arr.length == 2) {
                locale = new Locale(arr[0], arr[1]);
            }
        } else {
            return new Locale("zh", "CN");
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
