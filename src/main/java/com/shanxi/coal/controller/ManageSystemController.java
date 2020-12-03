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

    @GetMapping("/goedit")
    public String goEdit(@PathParam("uuid") String uuid, Model model) {
        if (StringUtils.isEmpty(uuid)) {
            return "redirect:/manageSystem/go";
        }
        ManageSystem manageSystem = manageSystemMapper.selectById(uuid);
        if (manageSystem == null) {
            return "redirect:/manageSystem/go";
        }
        model.addAttribute("manageSystemSel", manageSystem);
        List<ManageSystemItems> list = manageSystemItemsMapper.listByUseId(uuid);
        model.addAttribute("manageSystemItemsSel", list);
        return "manageSystem/add";
    }

    @PostMapping("/add")
    public String add(ManageSystem manageSystem, @RequestParam("itemsName") String itemsName,
                         @RequestParam("votingFormula") String votingFormula,
                      @RequestParam("peopleCount") String peopleCount, Model model) {
        System.out.println(itemsName);
        String[] itemsNames = itemsName.split(",");
        String[] votingFormulas = votingFormula.split(",");
        String[] peopleCounts = peopleCount.split(",");
        MyUtils.setCommonBean(manageSystem);
        manageSystemMapper.insertSelective(manageSystem);
        return insertDetail(itemsNames, votingFormulas, peopleCounts, manageSystem.getUuid());
    }

    @PutMapping("/add")
    public String update(ManageSystem manageSystem, @RequestParam("itemsName") String itemsName,
                         @RequestParam("votingFormula") String votingFormula,
                         @RequestParam("peopleCount") String peopleCount) {
        String[] itemsNames = itemsName.split(",");
        String[] votingFormulas = votingFormula.split(",");
        String[] peopleCounts = peopleCount.split(",");
        manageSystemMapper.updateByPrimaryKeySelective(manageSystem);
        String mainUUid = manageSystem.getUuid();
        manageSystemItemsMapper.deleteByParentId(mainUUid);
        return insertDetail(itemsNames, votingFormulas, peopleCounts, manageSystem.getUuid());
    }

    private String insertDetail(String[] itemsNames, String[] votingFormulas, String[] peopleCounts,String mainUUid) {
        for (int i = 0; i < itemsNames.length; i++) {
            ManageSystemItems manageSystemItems = new ManageSystemItems();
            manageSystemItems.setUuid(UUID.randomUUID().toString());
            manageSystemItems.setSystemParentId(mainUUid);
            manageSystemItems.setItemsName(checkNullAndReturn(itemsNames, i));
            manageSystemItems.setVotingFormula(checkNullAndReturn(votingFormulas, i));
            manageSystemItems.setPeopleCount(checkNullAndReturn(peopleCounts, i));
            manageSystemItemsMapper.insertSelective(manageSystemItems);
        }
        return "redirect:/manageSystem/go";
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
                       @RequestParam("pageSize") Integer pageSize,
                       @RequestParam(value = "systemType", required = false) String systemType) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageSystem where = new ManageSystem();
        MyUtils.buildCommonWhere(where);
        where.setStatus(0);
        where.setSystemType(systemType);
        List<ManageSystem> manageSystems = manageSystemMapper.getList(where);
        PageInfo<ManageSystem> pageInfo = new PageInfo<ManageSystem>(manageSystems);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        ManageSystem manageSystem = manageSystemMapper.selectByPrimaryKey(uuid);
        List<String> list = manageSystemItemsMapper.listByParentId(uuid);
        for (int i = 0; i < list.size(); i++) {
            ManageSystemItems manageSystemItems = manageSystemItemsMapper.selectByPrimaryKey(list.get(i));
            manageSystemItems.setIsDel(1);
            manageSystemItemsMapper.updateByPrimaryKey(manageSystemItems);
        }
        manageSystem.setIsDel(1);
        manageSystemMapper.updateByPrimaryKeySelective(manageSystem);
        return "ok";
    }

}
