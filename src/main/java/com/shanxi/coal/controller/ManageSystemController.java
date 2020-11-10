package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.ManageSystemItemsMapper;
import com.shanxi.coal.dao.ManageSystemMapper;
import com.shanxi.coal.domain.ManageSystem;
import com.shanxi.coal.domain.ManageSystemItems;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manageSystem")
public class ManageSystemController {

    @Resource
    ManageSystemMapper manageSystemMapper;
    @Resource
    ManageSystemItemsMapper manageSystemItemsMapper;

    @GetMapping("/go")
    public String go() {
        return "manageSystem/list";
    }

    @GetMapping("/goadd")
    public String goAdd(Model model) {
        model.addAttribute("user", MyUtils.getSessionUser());
        return "manageSystem/add";
    }

//    @GetMapping("/goedit")
//    public String goEdit(@PathParam("uuid") String uuid, Model model) {
//        if (StringUtils.isEmpty(uuid)) {
//            return "redirect:/manageLeader/go";
//        }
//        ManageLeader manageLeader = manageLeaderMapper.selectById(uuid);
//        if (manageLeader == null) {
//            return "redirect:/manageLeader/go";
//        }
//        model.addAttribute("manageLeaderSel", manageLeader);
//        List<ManageLeaderGroup> list = manageLeaderGroupMapper.listByUseId(uuid);
//        model.addAttribute("manageLeaderGrpoupSel", list);
//        return "manageLeader/add";
//    }

    @PostMapping("/add")
    public String add(ManageSystem manageSystem, @RequestParam("leaderGroup") String leaderGroup,
                         @RequestParam("jobTitle") String jobTitle, @RequestParam("officeStartDate") String officeStartDate,
                         @RequestParam("officeEndDate") String officeEndDate, Model model1) {
        String[] leaderGroups = leaderGroup.split(",");
        String[] jobTitles = jobTitle.split(",");
        String[] officeStartDates = officeStartDate.split(",");
        String[] officeEndDates = officeEndDate.split(",");
        MyUtils.setCommonBean(manageSystem);
        manageSystemMapper.insertSelective(manageSystem);
        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageSystem.getUuid());
    }

//    @PutMapping("/add")
//    public String update(ManageLeader manageLeader, @RequestParam("leaderGroup") String leaderGroup,
//                         @RequestParam("jobTitle") String jobTitle, @RequestParam("officeStartDate") String officeStartDate,
//                         @RequestParam("officeEndDate") String officeEndDate) {
//        String[] leaderGroups = leaderGroup.split(",");
//        String[] jobTitles = jobTitle.split(",");
//        String[] officeStartDates = officeStartDate.split(",");
//        String[] officeEndDates = officeEndDate.split(",");
//        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
//        String mainUUid = manageLeader.getUuid();
//        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageLeader.getUuid());
//    }

    private String insertDetail(String[] leaderGroups, String[] jobTitles, String[] officeStartDates,
                                String[] officeEndDates, String mainUUid) {
        for (int i = 0; i < leaderGroups.length; i++) {
            ManageSystemItems manageSystemItems = new ManageSystemItems();
            manageSystemItems.setUuid(UUID.randomUUID().toString());
//            manageSystemItems.setLeaderId(mainUUid);
//            manageSystemItems.setLeaderGroup(checkNullAndReturn(leaderGroups, i));
//            manageSystemItems.setJobTitle(checkNullAndReturn(jobTitles, i));
//            manageSystemItems.setOfficeStartDate(checkNullAndReturn(officeStartDates, i));
//            manageSystemItems.setOfficeEndDate(checkNullAndReturn(officeEndDates, i));
            manageSystemItemsMapper.insertSelective(manageSystemItems);
        }
        return "redirect:/manageSystem/go";
    }

//    public String checkNullAndReturn(String[] array, int idx) {
//        String result = "";
//        if (array.length != 0 && array.length >= idx + 1) {
//            if (array[idx] != null && array[idx] != "") {
//                result = array[idx];
//            }
//        }
//        return result;
//    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageSystem where = new ManageSystem();
        MyUtils.buildCommonWhere(where);
        where.setStatus(0);
        List<ManageSystem> manageSystems = manageSystemMapper.getList(where);
        PageInfo<ManageSystem> pageInfo = new PageInfo<ManageSystem>(manageSystems);
        return MyUtils.pageInfoToJson(pageInfo);
    }

//    @PostMapping("/delete")
//    @ResponseBody
//    public String delete(@PathParam("uuid") String uuid) {
//        ManageLeader manageLeader = manageLeaderMapper.selectByPrimaryKey(uuid);
//        List<String> list = manageLeaderGroupMapper.listByParentId(uuid);
//        for (int i = 0; i < list.size(); i++) {
//            ManageLeaderGroup manageLeaderGroup = manageLeaderGroupMapper.selectByPrimaryKey(list.get(i));
//            manageLeaderGroup.setIsDel(1);
//            manageLeaderGroupMapper.updateByPrimaryKey(manageLeaderGroup);
//        }
//        manageLeader.setIsDel(1);
//        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
//        return "ok";
//    }

}
