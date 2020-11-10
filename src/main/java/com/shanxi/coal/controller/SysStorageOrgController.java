package com.shanxi.coal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanxi.coal.dao.SysDataRolePowerMapper;
import com.shanxi.coal.dao.SysOrgMapper;
import com.shanxi.coal.domain.SysDataRolePower;
import com.shanxi.coal.domain.SysOrg;
import com.shanxi.coal.domain.TreeNodeState;
import com.shanxi.coal.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sysstorageorg")
public class SysStorageOrgController {

    @Resource
    SysOrgMapper sysOrgMapper;
    @Resource
    SysDataRolePowerMapper sysDataRolePowerMapper;

    @GetMapping("/go")
    public String go() {
        return "system/storageOrg/list";
    }


    @GetMapping("/list")
    @ResponseBody
    public String list(@RequestParam(value = "uuid", required = false) String uuid) {
        String json = getTreeJson(uuid, null);
        return json;
    }

    @GetMapping("/list2")
    @ResponseBody
    public String list() {
        String json = getTreeJson(null, MyUtils.getSessionUser().getParentCompanyId());
        return json;
    }


    private String getTreeJson(String uuid, String storageId) {
        List<SysOrg> sysOrgs = null;
        if (StringUtils.isNotBlank(storageId))
            sysOrgs = sysOrgMapper.listStorageOrgById(storageId);
        else
            sysOrgs = sysOrgMapper.listStorageOrg();
        List<SysOrg> list = new ArrayList<>();
        for (SysOrg org : sysOrgs) {
            List<SysOrg> dl = getParent(org.getUuid());
            list.addAll(dl);
        }
        List<SysOrg> uniqueOrg = MyUtils.removeDuplicate(list);
        List<SysOrg> sysOrgs1 = buildTree(uniqueOrg, uuid);
        return MyUtils.objectToJson(sysOrgs1);

    }

    private List<SysOrg> buildTree(List<SysOrg> dl, String uuid) {
        SysOrg parent = new SysOrg();
        List<SysOrg> list = new ArrayList<>();
        List<String> selected = sysDataRolePowerMapper.listOrgIdByRole(uuid);
        for (SysOrg org : dl) {
            if (org != null && org.getParent().equals("0")) {
                List<SysOrg> dd = getChildren(org.getUuid(), dl, selected);
                parent.setId(org.getUuid());
                parent.setText(org.getName());
                parent.setNodes(dd);
                if (selected.contains(org.getUuid())) {
                    TreeNodeState t = new TreeNodeState();
                    t.setChecked(true);
                    t.setExpanded(true);
                    parent.setState(t);
                }
                break;
            }
        }
        list.add(parent);
        return list;
    }

    private List<SysOrg> getChildren(String uuid, List<SysOrg> dl, List<String> selected) {
        List<SysOrg> dll = new ArrayList<SysOrg>();
        List<SysOrg> child = new ArrayList<>();
        for (SysOrg s : dl) {
            if (s != null && s.getParent().equals(uuid)) {
                child.add(s);
            }
        }
        for (SysOrg ss : child) {
            List<SysOrg> chls = getChildren(ss.getUuid(), dl, selected);
            if (!chls.isEmpty()) {
                ss.setNodes(chls);
            }
            ss.setId(ss.getUuid());
            ss.setText(ss.getName());
            if (selected.contains(ss.getUuid())) {
                TreeNodeState t = new TreeNodeState();
                t.setChecked(true);
                ss.setState(t);
            }
            dll.add(ss);
        }
        return dll;
    }

    private List<SysOrg> getParent(String uuid) {
        List<SysOrg> dl = new ArrayList<>();
        SysOrg dep = sysOrgMapper.selectByPrimaryKey(uuid);
        if (dep != null) {
            List<SysOrg> parent = getParent(dep.getParent());
            dl.addAll(parent);
        }
        dl.add(dep);
        return dl;
    }

}
