package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.ManageLeaderGroupMapper;
import com.shanxi.coal.dao.ManageLeaderMapper;
import com.shanxi.coal.domain.ManageLeader;
import com.shanxi.coal.domain.ManageLeaderGroup;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manageLeader")
public class ManageLeaderController {

    @Resource
    ManageLeaderMapper manageLeaderMapper;
    @Resource
    ManageLeaderGroupMapper manageLeaderGroupMapper;

    @GetMapping("/go")
    public String go() {
        return "manageLeader/list";
    }

    @GetMapping("/goadd")
    public String goAdd(Model model) {
        model.addAttribute("user", MyUtils.getSessionUser());
        return "manageLeader/add";
    }

    @GetMapping("/goedit")
    public String goEdit(@PathParam("uuid") String uuid, Model model) {
        if (StringUtils.isEmpty(uuid)) {
            return "redirect:/manageLeader/go";
        }
        ManageLeader manageLeader = manageLeaderMapper.selectById(uuid);
        if (manageLeader == null) {
            return "redirect:/manageLeader/go";
        }
        model.addAttribute("manageLeaderSel", manageLeader);
        List<ManageLeaderGroup> list = manageLeaderGroupMapper.listByUseId(uuid);
        model.addAttribute("manageLeaderGrpoupSel", list);
        return "manageLeader/add";
    }

    @PostMapping("/add")
    public String add(ManageLeader manageLeader, @RequestParam("leaderGroup") String leaderGroup,
                         @RequestParam("jobTitle") String jobTitle, @RequestParam("officeStartDate") String officeStartDate,
                         @RequestParam("officeEndDate") String officeEndDate, Model model1) {
        String[] leaderGroups = leaderGroup.split(",");
        String[] jobTitles = jobTitle.split(",");
        String[] officeStartDates = officeStartDate.split(",");
        String[] officeEndDates = officeEndDate.split(",");
        MyUtils.setCommonBean(manageLeader);
        manageLeaderMapper.insertSelective(manageLeader);
        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageLeader.getUuid());
    }

    @PutMapping("/add")
    public String update(ManageLeader manageLeader,
                         @RequestParam("leaderGroup") String leaderGroup,
                         @RequestParam("jobTitle") String jobTitle,
                         @RequestParam("officeStartDate") String officeStartDate,
                         @RequestParam("officeEndDate") String officeEndDate) {
        String[] leaderGroups = leaderGroup.split(",");
        String[] jobTitles = jobTitle.split(",");
        String[] officeStartDates = officeStartDate.split(",");
        String[] officeEndDates = officeEndDate.split(",");
        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
        String mainUUid = manageLeader.getUuid();
        manageLeaderGroupMapper.deleteByParentId(mainUUid);
        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageLeader.getUuid());
    }

    private String insertDetail(String[] leaderGroups, String[] jobTitles, String[] officeStartDates,
                                String[] officeEndDates, String mainUUid) {
        for (int i = 0; i < leaderGroups.length; i++) {
            ManageLeaderGroup manageLeaderGroup = new ManageLeaderGroup();
            manageLeaderGroup.setUuid(UUID.randomUUID().toString());
            manageLeaderGroup.setLeaderId(mainUUid);
            manageLeaderGroup.setLeaderGroup(checkNullAndReturn(leaderGroups, i));
            manageLeaderGroup.setJobTitle(checkNullAndReturn(jobTitles, i));
            manageLeaderGroup.setOfficeStartDate(checkNullAndReturn(officeStartDates, i));
            manageLeaderGroup.setOfficeEndDate(checkNullAndReturn(officeEndDates, i));
            manageLeaderGroupMapper.insertSelective(manageLeaderGroup);
        }
        return "redirect:/manageLeader/go";
    }

    public String checkNullAndReturn(String[] array, int idx) {
        String result = "";
        if (array.length != 0 && array.length >= idx + 1) {
            if (array[idx] != null && array[idx] != "") {
                result = array[idx];
            }
        }
        return result;
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageLeader where = new ManageLeader();
        MyUtils.buildCommonWhere(where);
        where.setStatus(0);
        List<ManageLeader> manageLeader = manageLeaderMapper.getList(where);
        PageInfo<ManageLeader> pageInfo = new PageInfo<ManageLeader>(manageLeader);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        ManageLeader manageLeader = manageLeaderMapper.selectByPrimaryKey(uuid);
        List<String> list = manageLeaderGroupMapper.listByParentId(uuid);
        for (int i = 0; i < list.size(); i++) {
            ManageLeaderGroup manageLeaderGroup = manageLeaderGroupMapper.selectByPrimaryKey(list.get(i));
            manageLeaderGroup.setIsDel(1);
            manageLeaderGroupMapper.updateByPrimaryKey(manageLeaderGroup);
        }
        manageLeader.setIsDel(1);
        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
        return "ok";
    }

}
