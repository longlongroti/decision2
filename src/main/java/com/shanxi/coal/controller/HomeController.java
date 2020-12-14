package com.shanxi.coal.controller;

import com.shanxi.coal.dao.HomeMapper;
import com.shanxi.coal.utils.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Resource
    HomeMapper homeMapper;

    @PostMapping("/counttotal")
    @ResponseBody
    public String countTotal() {
        int aa = homeMapper.countLeader("0");
        int ab = homeMapper.countLeader("1");
        int ac = homeMapper.countLeader("2");
        int ad = homeMapper.countLeader("3");
        int ae = homeMapper.countLeader("4");
        int af = homeMapper.countLeader("5");
        int ag = homeMapper.countLeader("6");
        int ah = homeMapper.countLeader("7");
        int ai = homeMapper.countLeader("8");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("aa", aa);
        map.put("ab", ab);
        map.put("ac", ac);
        map.put("ad", ad);
        map.put("ae", ae);
        map.put("af", af);
        map.put("ag", ag);
        map.put("ah", ah);
        map.put("ai", ai);
        int ba = homeMapper.countEventByCatelog("重大决策");
        int bb = homeMapper.countEventByCatelog("重要人事任免");
        int bc = homeMapper.countEventByCatelog("重大项目安排");
        int bd = homeMapper.countEventByCatelog("大额度资金运作");
        map.put("ba", ba);
        map.put("bb", bb);
        map.put("bc", bc);
        map.put("bd", bd);
        int da = homeMapper.countMeetingType("0");
        int db = homeMapper.countMeetingType("1");
        int dc = homeMapper.countMeetingType("2");
        int dd = homeMapper.countMeetingType("3");
        int de = homeMapper.countMeetingType("4");
        int df = homeMapper.countMeetingType("5");
        int dg = homeMapper.countMeetingType("6");
        int dh = homeMapper.countMeetingType("7");
        int di = homeMapper.countMeetingType("8");
        map.put("da", da);
        map.put("db", db);
        map.put("dc", dc);
        map.put("db", dd);
        map.put("de", de);
        map.put("df", df);
        map.put("dg", dg);
        map.put("dh", dh);
        map.put("di", di);
        int ca = homeMapper.countSystemByType("0");
        int cb = homeMapper.countSystemByType("1");
        int cc = homeMapper.countSystemByType("2");
        int cd = homeMapper.countSystemByType("3");
        int ce = homeMapper.countSystemByType("4");
        int cf = homeMapper.countSystemByType("5");
        int cg = homeMapper.countSystemByType("6");
        map.put("ca", ca);
        map.put("cb", cb);
        map.put("cc", cc);
        map.put("cd", cd);
        map.put("ce", ce);
        map.put("cf", cf);
        map.put("cg", cg);
        return MyUtils.objectToJson(map);
    }

}
