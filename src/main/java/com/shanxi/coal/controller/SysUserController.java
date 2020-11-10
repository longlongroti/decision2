package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.enums.AuditRoleEnum;
import com.shanxi.coal.enums.MyDelEnum;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.MD5Util;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/sysuser")
public class SysUserController {

    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    SysOrgMapper sysOrgMapper;
    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    SysDataRoleMapper sysDataRoleMapper;
    @Resource
    SysUserRoleMapper sysUserRoleMapper;
    @Resource
    SysUserAuditRoleMapper sysUserAuditRoleMapper;
    @Resource
    SysUserDataRoleMapper sysUserDataRoleMapper;

    @GetMapping("/go")
    public String go() {
        return "system/user/list";
    }

    @GetMapping("/gocheck")
    public String gocheck(Model model) {
        String uuid = MyUtils.getSessionUser().getUuid();
        String orgId = MyUtils.getSessionUser().getOrgId();
        SysUser sysUser = sysUserMapper.selectUserExtra(uuid);
        model.addAttribute("userSelected", sysUser);
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(orgId);
        model.addAttribute("sysOrgSelected", sysOrg);
        return "system/user/detail";
    }

    @GetMapping("/gopwd")
    public String gopwd() {
        return "system/user/updatepwd";
    }

    @GetMapping("/goerr")
    public String goerr(Model model) {
        model.addAttribute("error", true);
        return "system/user/updatepwd";
    }

    @PostMapping("/updatepwd")
    public String updatepwd(@RequestParam("newPassword") String newPwd,
                            @RequestParam("oldPassword") String oldPwd,
                            @RequestParam("confirmPassword") String confirmPwd) {
        String regexZS = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,32}$";
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(MyUtils.getSessionUser().getUuid());
        if (StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(confirmPwd)
                || !newPwd.equals(confirmPwd) || !newPwd.matches(regexZS) || sysUser == null
                || !sysUser.getPassword().equals(MD5Util.computeMD5(oldPwd))) {
            return "redirect:/sysuser/goerr";
        }
        if (sysUser != null) {
            sysUser.setPassword(MD5Util.computeMD5(newPwd));
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
        return "redirect:/user/logout";
    }

    @GetMapping("/goadd")
    public String goAdd(@PathParam("org") String org, Model model) {
        /*获取add page 需要的数据 渲染页面*/
        /*todo 抽出页面渲染的方法*/
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(org);
        if (sysOrg != null) {
            List<SysRole> sysRoles = sysRoleMapper.listRoles();
            List<SysDataRole> sysDataRoles = sysDataRoleMapper.listRoles();
            model.addAttribute("sysOrgSelected", sysOrg);
            model.addAttribute("userRolesSelection", sysRoles);
            model.addAttribute("userDataRolesSelection", sysDataRoles);
            Map<String, String> map = AuditRoleEnum.getMap();
            model.addAttribute("map", map);

            List<SysOrg> company = sysOrgMapper.listStorageOrg();
            model.addAttribute("company", company);
            List<SysOrg> rentCompany = sysOrgMapper.listRentOrg();
            model.addAttribute("rentCompany", rentCompany);
            List<SysOrg> twoLevelCompany = sysOrgMapper.listTwoLevelOrg();
            model.addAttribute("twoLevelCompany", twoLevelCompany);
            return "system/user/add";
        }
        return "system/user/list";
    }

    @GetMapping("/goedit")
    public String goEdit(@PathParam("uuid") String uuid, Model model) {
        if (StringUtils.isEmpty(uuid)) {
            return "redirect:/go/sysuser/list";
        }
        List<SysRole> sysRoles = sysRoleMapper.listRoles();
        List<SysDataRole> sysDataRoles = sysDataRoleMapper.listRoles();
        model.addAttribute("userRolesSelection", sysRoles);
        model.addAttribute("userDataRolesSelection", sysDataRoles);
        Map<String, String> map = AuditRoleEnum.getMap();
        model.addAttribute("map", map);
        SysUser sysUser = sysUserMapper.selectUserExtra(uuid);
        model.addAttribute("sysUserSelected", sysUser);

        List<SysOrg> company = sysOrgMapper.listStorageOrg();
        model.addAttribute("company", company);
        List<SysOrg> rentCompany = sysOrgMapper.listRentOrg();
        model.addAttribute("rentCompany", rentCompany);
        List<SysOrg> twoLevelCompany = sysOrgMapper.listTwoLevelOrg();
        model.addAttribute("twoLevelCompany", twoLevelCompany);
        return "system/user/add";
    }

    @PostMapping("/do")
    @Transactional
    public String add(HttpServletRequest request, SysUser sysUser) {
        if (sysUser != null) {
            sysUser.setUuid(UUID.randomUUID().toString());
            sysUser.setPassword(MD5Util.computeMD5("1"));
            sysUserMapper.insertSelective(sysUser);
            batchInsertUserRole(sysUser);
            batchInsertUserDataRole(sysUser);
            batchInsertUserAuditRole(sysUser);
        }
        return "redirect:/sysuser/go";
    }

    //
//    private void uploadSign(HttpServletRequest request, MultipartFile file, SysUser sysUser) {
//        if(StringUtils.isNotEmpty(file.getOriginalFilename())) {
//            String filePath = uploadFile(request, file, myProperties.getFileUploadedPath());
//            FileUploaded fileUploaded = new FileUploaded();
//            fileUploaded.setFileCategory(MyFileTypeEnum.SIGNATURE.getCode().toString());
//            fileUploaded.setFileCategoryId(sysUser.getUuid());
//            FileUploaded fileUploaded1 = MyUtils.buildFileUploaded(fileUploaded, file.getOriginalFilename(), filePath, file.getSize(), file.getContentType(), "0");
//            fileUploadedMapper.updateSignatureBy(MyFileTypeEnum.SIGNATURE.getCode().toString(), sysUser.getUuid());
//            fileUploadedMapper.insertSelective(fileUploaded1);
//        }
//    }
//
    @PutMapping("/do")
    @Transactional
    public String update(HttpServletRequest request, SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        sysUserRoleMapper.deleteByUserId(sysUser.getUuid());
        sysUserAuditRoleMapper.deleteByUserId(sysUser.getUuid());
        sysUserDataRoleMapper.deleteByUserId(sysUser.getUuid());
        batchInsertUserRole(sysUser);
        batchInsertUserDataRole(sysUser);
        batchInsertUserAuditRole(sysUser);
        return "redirect:/sysuser/go";
    }

    //
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("uuid") String uuid) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(uuid);
        if (sysUser != null) {
            sysUser.setIsDel(MyDelEnum.DELETED_STATUS.getCode());
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
            sysUserRoleMapper.deleteByUserId(sysUser.getUuid());
            sysUserAuditRoleMapper.deleteByUserId(sysUser.getUuid());
            sysUserDataRoleMapper.deleteByUserId(sysUser.getUuid());
        }
        return "ok";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize,
                       @RequestParam(name = "name", required = false) String name,
                       @RequestParam(name = "org", required = false) String org) {
        PageHelper.startPage(pageNumber, pageSize);
        SysUser where = new SysUser();
        where.setName(StringUtils.isNotEmpty(name) ? name.trim() : null);
        where.setOrgId(StringUtils.isNotEmpty(org) ? org : null);
        List<SysUser> sysUsers = sysUserMapper.listUser(where);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUsers);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/listauditor")
    @ResponseBody
    public String listAuditor(@RequestParam("pageNumber") Integer pageNumber,
                              @RequestParam("pageSize") Integer pageSize,
                              @RequestParam(name = "name", required = false) String name) {
        PageHelper.startPage(pageNumber, pageSize);
        SysUser where = new SysUser();
        where.setName(StringUtils.isNotEmpty(name) ? name.trim() : null);
        List<SysAuditor> sysAuditors = sysUserMapper.listAuditor(where);
        PageInfo<SysAuditor> pageInfo = new PageInfo<>(sysAuditors);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    //
//    @GetMapping("/detail")
//    @ResponseBody
//    public String list(@RequestParam("uuid") String uuid, Model model) {
//        SysUser sysUser = sysUserMapper.selectByPrimaryKey(uuid);
//        return beanToJson(sysUser);
//    }
//
    private void batchInsertUserRole(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getRoles())) {
            return;
        }
        String[] arr = sysUser.getRoles().split(",");
        if (arr.length > 0) {
            List<SysUserRole> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getUuid());
                sysUserRole.setRoleId(s);
                sysUserRole.setUuid(UUID.randomUUID().toString());
                list.add(sysUserRole);
            }
            sysUserRoleMapper.insertBatch(list);
        }
    }

    //
    private void batchInsertUserAuditRole(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getAuditRoles())) {
            return;
        }
        String[] arr = sysUser.getAuditRoles().split(",");
        if (arr.length > 0) {
            List<SysUserAuditRole> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                SysUserAuditRole sysUserAuditRole = new SysUserAuditRole();
                sysUserAuditRole.setUserId(sysUser.getUuid());
                sysUserAuditRole.setAuditRoleId(s);
                sysUserAuditRole.setAuditRoleName(AuditRoleEnum.getMsgByCode(s));
                sysUserAuditRole.setUuid(UUID.randomUUID().toString());
                list.add(sysUserAuditRole);
            }
            sysUserAuditRoleMapper.insertBatch(list);
        }
    }

    private void batchInsertUserDataRole(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getDataRoles())) {
            return;
        }
        String[] arr = sysUser.getDataRoles().split(",");
        if (arr.length > 0) {
            List<SysUserDataRole> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                SysUserDataRole sysUserDataRole = new SysUserDataRole();
                sysUserDataRole.setUserId(sysUser.getUuid());
                sysUserDataRole.setRoleId(s);
                sysUserDataRole.setUuid(UUID.randomUUID().toString());
                list.add(sysUserDataRole);
            }
            sysUserDataRoleMapper.insertBatch(list);
        }
    }
}
