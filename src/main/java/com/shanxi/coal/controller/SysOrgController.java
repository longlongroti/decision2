package com.shanxi.coal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanxi.coal.dao.SysOrgMapper;
import com.shanxi.coal.domain.SysOrg;
import com.shanxi.coal.enums.StorageOrgEnum;
import com.shanxi.coal.utils.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sysorg")
public class SysOrgController {

    @Resource
    SysOrgMapper sysOrgMapper;

    @GetMapping("/go")
    public String go(Model model) {
        Map<String, String> pro = StorageOrgEnum.getMap();
        model.addAttribute("storageOrg", pro);
        return "system/org/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpSession session) {
        String json = getTreeJson();
        return json;
    }

    @GetMapping("/list2")
    @ResponseBody
    public String list2(@RequestParam(value = "type",required = false)String type, HttpServletRequest request, HttpSession session) {
        String json = getTreeJson2(type);
        return json;
    }


    @PostMapping("/add")
    public String add(SysOrg sysOrg, Model model) {
        sysOrg.setParent(sysOrg.getUuid());
        sysOrg.setUuid(UUID.randomUUID().toString());
        sysOrgMapper.insertSelective(sysOrg);
        return "redirect:/sysorg/go";
    }

    @PostMapping("/del")
    public String del(SysOrg sysOrg) {
        sysOrgMapper.deleteByPrimaryKey(sysOrg.getUuid());//todo delete update
        return "redirect:/sysorg/go";
    }

    @PostMapping("/edit")
    public String edit(SysOrg sysOrg) {
        sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
        return "redirect:/sysorg/go";
    }

    @PostMapping("/getByParent")
    @ResponseBody
    public String list(@RequestParam("parentId") String parentId, HttpServletRequest request, HttpSession session, Model model) {
        List<SysOrg> sysOrgs = sysOrgMapper.listOrgByParent(parentId);
        for (SysOrg org : sysOrgs) {
            List<SysOrg> dl = new ArrayList<>();
            org.setText(org.getName());
            org.setNodes(dl);
        }
        return MyUtils.listToJson(sysOrgs);
    }


    private String getTreeJson() {
        SysOrg sysOrg = sysOrgMapper.findRoot();
        if (sysOrg == null) {
            return null;
        }
        List<SysOrg> dl = new ArrayList<>();
        sysOrg.setText(sysOrg.getName());
        sysOrg.setNodes(dl);
        List<SysOrg> list = new ArrayList<>();
        list.add(sysOrg);
        return MyUtils.listToJson(list);
    }


    private String getTreeJson2(String type) {
        String json = null;
        String orgId= MyUtils.getSessionUser().getCompanyId();
        if(type.equals("rent")){
            orgId= MyUtils.getSessionUser().getRentCompanyId();
        }
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(orgId);
        if (sysOrg != null) {
            List<SysOrg> dl = getChildren(sysOrg.getUuid());
            sysOrg.setText(sysOrg.getName());
            if (!dl.isEmpty()) {
                sysOrg.setTags(Arrays.asList(dl.size()));
                sysOrg.setNodes(dl);
            }
            List<SysOrg> list = new ArrayList<>();
            list.add(sysOrg);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    private List<SysOrg> getChildren(String uuid) {
        List<SysOrg> dl = new ArrayList<SysOrg>();
        List<SysOrg> depList = sysOrgMapper.listOrgByParent(uuid);
        for (int i = 0, l = depList.size(); i < l; i++) {
            List<SysOrg> chls = getChildren(depList.get(i).getUuid());
            if (!chls.isEmpty()) {
                depList.get(i).setTags(Arrays.asList(chls.size()));
                depList.get(i).setNodes(chls);
            }
            depList.get(i).setText(depList.get(i).getName());
            dl.add(depList.get(i));
        }
        return dl;
    }
}
