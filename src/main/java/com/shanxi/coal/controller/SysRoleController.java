package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.dao.SysRoleMapper;
import com.shanxi.coal.dao.SysRolePowerMapper;
import com.shanxi.coal.domain.SysRole;
import com.shanxi.coal.domain.SysRolePower;
import com.shanxi.coal.domain.TreeNode;
import com.shanxi.coal.domain.TreeNodeState;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.shanxi.coal.utils.MyUtils.listToJson;
import static java.util.Comparator.*;


@Controller
@RequestMapping("/sysrole")
public class SysRoleController {

    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    MyProperties myProperties;
    @Resource
    SysRolePowerMapper sysRolePowerMapper;

    @GetMapping("/go")
    public String go() {
        return "system/role/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpSession session, Model model) {
        String json = getTreeJson();
        return json;
    }

    @GetMapping("/powertree")
    @ResponseBody
    public String powerTree(@RequestParam(value = "uuid", required = false) String uuid, HttpServletRequest request, HttpSession session, Model model) {
        List<String> pows = new ArrayList<>();
        if (StringUtils.isNotEmpty(uuid)) {
            List<SysRolePower> sysRolePowers = sysRolePowerMapper.listByRoleId(uuid);
            for (SysRolePower p : sysRolePowers) {
                pows.add(p.getPower());
            }
        }
        String json = getPowerTreeJson(pows);
        return json;
    }

    public Map<String, List<String>> sortMapByKey(Map<String, List<String>> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, List<String>> sortedMap = new TreeMap<>(naturalOrder());
        sortedMap.putAll(oriMap);
        return sortedMap;
    }
    private String getPowerTreeJson(List<String> pows) {
        Map<String, List<String>> powerSpec = myProperties.getPowerSpec();
        powerSpec = sortMapByKey(powerSpec);
        List<TreeNode> list2 = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : powerSpec.entrySet()) {
            TreeNode treeNode = new TreeNode();
            treeNode.setText(entry.getKey());
            TreeNodeState s = new TreeNodeState();
            s.setChecked(false);
            if (pows.contains(entry.getKey())) {
                s.setChecked(true);
            }
            treeNode.setState(s);
            List<TreeNode> l = new ArrayList<>();
            for (String e : entry.getValue()) {
                String text = entry.getKey() + "_" + e;
                TreeNode treeNode1 = new TreeNode();
                treeNode1.setText(text);
                TreeNodeState s2 = new TreeNodeState();
                s2.setChecked(false);
                if (pows.contains(text)) {
                    s2.setChecked(true);
                }
                treeNode1.setState(s2);
                l.add(treeNode1);
            }
            treeNode.setNodes(l);
            list2.add(treeNode);
        }
        return listToJson(list2);
    }

    private String getTreeJson() {
        SysRole sysRole = new SysRole();
        List<SysRole> list = sysRoleMapper.listRoles();
        for (SysRole r : list) {
            r.setText(r.getName());
        }
        sysRole.setText("角色树");
        sysRole.setNodes(list);
        List<SysRole> list2 = new ArrayList<>();
        list2.add(sysRole);
        return listToJson(list2);
    }

    @PostMapping("/add")
    @Transactional
    public String add(SysRole sysRole) {
        if (sysRole != null) {
            sysRole.setUuid(UUID.randomUUID().toString());
            sysRoleMapper.insertSelective(sysRole);
            batchInsertRolePowerss(sysRole);
        }
        return "redirect:/sysrole/go";
    }

    @PostMapping("/edit")
    public String edit(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getUuid())) {
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            sysRolePowerMapper.deleteByRole(sysRole.getUuid(), null);
            batchInsertRolePowerss(sysRole);
        }
        return "redirect:/sysrole/go";
    }

    @PostMapping("/del")
    public String del(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getUuid())) {
            sysRoleMapper.deleteByPrimaryKey(sysRole.getUuid());
            sysRolePowerMapper.deleteByRole(sysRole.getUuid(), null);//todo delete update
        }
        return "redirect:/sysrole/go";
    }

    private void batchInsertRolePowerss(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getPows())) {
            return;
        }
        String[] arr = sysRole.getPows().split(",");
        if (arr.length > 0) {
            List<SysRolePower> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                SysRolePower sysRolePower = new SysRolePower(sysRole.getUuid(), s, UUID.randomUUID().toString(), 1);
                list.add(sysRolePower);
            }
            sysRolePowerMapper.insertBatch(list);
        }
    }
}
