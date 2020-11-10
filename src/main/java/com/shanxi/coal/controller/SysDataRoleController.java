package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.enums.StorageOrgEnum;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.shanxi.coal.utils.MyUtils.listToJson;


@Controller
@RequestMapping("/sysdatarole")
public class SysDataRoleController {

    @Resource
    SysDataRoleMapper sysDataRoleMapper;
    @Resource
    SysOrgMapper sysOrgMapper;
    @Resource
    MyProperties myProperties;
    @Resource
    SysDataRolePowerMapper sysDataRolePowerMapper;

    @GetMapping("/go")
    public String go() {
        return "system/dataRole/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpSession session, Model model) {
        String json = getTreeJson();
        return json;
    }


    private String getTreeJson() {
        SysDataRole sysDataRole = new SysDataRole();
        List<SysDataRole> list = sysDataRoleMapper.listRoles();
        for (SysDataRole r : list) {
            r.setText(r.getName());
        }
        sysDataRole.setText("数据角色树");
        sysDataRole.setNodes(list);
        List<SysDataRole> list2 = new ArrayList<>();
        list2.add(sysDataRole);
        return listToJson(list2);
    }

    @PostMapping("/add")
    @Transactional
    public String add(SysDataRole sysDataRole) {
        if (sysDataRole != null) {
            sysDataRole.setUuid(UUID.randomUUID().toString());
            sysDataRoleMapper.insertSelective(sysDataRole);
            batchInsertDataRolePowerss(sysDataRole);
        }
        return "redirect:/sysdatarole/go";
    }
//
    @PostMapping("/edit")
    public String edit(SysDataRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getUuid())) {
            sysDataRoleMapper.updateByPrimaryKeySelective(sysRole);
            sysDataRolePowerMapper.deleteByRole(sysRole.getUuid(), null);
            batchInsertDataRolePowerss(sysRole);
        }
        return "redirect:/sysdatarole/go";
    }

    @PostMapping("/del")
    public String del(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getUuid())) {
            sysDataRoleMapper.deleteByPrimaryKey(sysRole.getUuid());
            sysDataRolePowerMapper.deleteByRole(sysRole.getUuid(), null);//todo delete update
        }
        return "redirect:/sysdatarole/go";
    }

    private void batchInsertDataRolePowerss(SysDataRole sysDataRole) {
        if (StringUtils.isEmpty(sysDataRole.getPows())) {
            return;
        }
        String[] arr = sysDataRole.getPows().split(",");
        if (arr.length > 0) {
            List<SysRolePower> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(s);
                if(sysOrg!=null && sysOrg.getStorageOrg().equals(StorageOrgEnum.IS_STORAGE_ENUM.getCode())) {
                    SysRolePower sysRolePower = new SysRolePower(sysDataRole.getUuid(), s, UUID.randomUUID().toString(), 1);
                    list.add(sysRolePower);
                }
            }
            sysDataRolePowerMapper.insertBatch(list);
        }
    }
}
