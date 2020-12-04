package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.DicEventsCatalogMapper;
import com.shanxi.coal.dao.ManageEventsDetailsMapper;
import com.shanxi.coal.dao.ManageEventsListMapper;
import com.shanxi.coal.domain.DicEventsCatalog;
import com.shanxi.coal.domain.ManageEventsDetails;
import com.shanxi.coal.domain.ManageEventsList;
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
@RequestMapping("/manageEventsList")
public class ManageEventsListController {

    @Resource
    ManageEventsListMapper manageEventsListMapper;
    @Resource
    ManageEventsDetailsMapper manageEventsDetailsMapper;
    @Resource
    DicEventsCatalogMapper dicEventsCatalogMapper;

    @GetMapping("/go")
    public String go() {
        return "manageEventsList/list";
    }

    @GetMapping("/goadd")
    public String goAdd(Model model) {
        model.addAttribute("user", MyUtils.getSessionUser());
        return "manageEventsList/add";
    }

    @GetMapping("/goedit")
    public String goEdit(@PathParam("uuid") String uuid, Model model) {
        if (StringUtils.isEmpty(uuid)) {
            return "redirect:/manageEventsList/go";
        }
        ManageEventsList manageEventsList = manageEventsListMapper.selectById(uuid);
        if (manageEventsList == null) {
            return "redirect:/manageEventsList/go";
        }
        model.addAttribute("manageEventsListSel", manageEventsList);
        List<ManageEventsDetails> list = manageEventsDetailsMapper.listByUseId(uuid);
        model.addAttribute("manageEventsDetailsSel", list);
        return "manageEventsList/add";
    }

    @PostMapping("/add")
    public String add(ManageEventsList manageEventsList,@RequestParam("eventId") String eventId,@RequestParam("listName") String listName,
                      @RequestParam("versionNumber") String versionNumber,@RequestParam("startTime") String startTime,
                      @RequestParam("endTime") String endTime,@RequestParam("remark") String remark) {
        manageEventsList.setListName(listName);
        manageEventsList.setVersionNumber(versionNumber);
        manageEventsList.setStartTime(startTime);
        manageEventsList.setEndTime(endTime);
        manageEventsList.setRemark(remark);
        MyUtils.setCommonBean(manageEventsList);
        manageEventsListMapper.insertSelective(manageEventsList);
        return insertDetail(manageEventsList.getUuid(), eventId);
    }

    @PutMapping("/add")
    public String update(ManageEventsList manageEventsList,
                         @RequestParam(value="mainUuid",required=false) String mainUuid,
                         @RequestParam(value="eventId",required=false) String eventId,
                         @RequestParam("listName") String listName,
                         @RequestParam("versionNumber") String versionNumber,
                         @RequestParam("startTime") String startTime,
                         @RequestParam("endTime") String endTime,
                         @RequestParam("remark") String remark) {
        manageEventsList.setUuid(mainUuid);
        manageEventsList.setListName(listName);
        manageEventsList.setVersionNumber(versionNumber);
        manageEventsList.setStartTime(startTime);
        manageEventsList.setEndTime(endTime);
        manageEventsList.setRemark(remark);
        manageEventsListMapper.updateByPrimaryKeySelective(manageEventsList);
        String mainUUid = manageEventsList.getUuid();
        manageEventsDetailsMapper.deleteByMainId(mainUUid);
        return insertDetail(manageEventsList.getUuid(), eventId);
    }

    private String insertDetail(String parentId,String eventId) {
        String[] parentIds = parentId.split(",");
        String[] eventIds = eventId.split(",");
        for (int i = 0; i < eventIds.length; i++) {
            ManageEventsDetails manageEventsDetails = new ManageEventsDetails();
            manageEventsDetails.setUuid(UUID.randomUUID().toString());
            manageEventsDetails.setParentId(parentId);//事项id
            manageEventsDetails.setEventId(eventIds[i]);//事项字典id
            manageEventsDetailsMapper.insertSelective(manageEventsDetails);
        }
        return "manageEventsList/list";
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
        ManageEventsList where = new ManageEventsList();
        MyUtils.buildCommonWhere(where);
        where.setStatus(0);
        List<ManageEventsList> manageEventsLists = manageEventsListMapper.getList(where);
        PageInfo<ManageEventsList> pageInfo = new PageInfo<ManageEventsList>(manageEventsLists);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/listEquipment")
    @ResponseBody
    public String listequipment(@RequestParam(value = "uuid", required = false) String uuid) throws ParseException {
        if (StringUtils.isEmpty(uuid)) {
            return null;
        }
        List<ManageEventsDetails> equiIds = manageEventsDetailsMapper.listEquipIdByMain(uuid);
        PageInfo<ManageEventsDetails> pageInfo = new PageInfo<ManageEventsDetails>(equiIds);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/eventsList")
    @ResponseBody
    public String eventsList(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        List<DicEventsCatalog> dicEventsCatalog = dicEventsCatalogMapper.getEventsList();
        PageInfo<DicEventsCatalog> pageInfo = new PageInfo<DicEventsCatalog>(dicEventsCatalog);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/listAll")
    @ResponseBody
    public String listAll() throws ParseException {
        List<DicEventsCatalog> dicEventsCatalog = dicEventsCatalogMapper.listAll();
        PageInfo<DicEventsCatalog> pageInfo = new PageInfo<DicEventsCatalog>(dicEventsCatalog);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        ManageEventsList manageEventsList = manageEventsListMapper.selectByPrimaryKey(uuid);
        List<ManageEventsDetails> list = manageEventsDetailsMapper.listByParentId(uuid);
        for (int i = 0; i < list.size(); i++) {
            ManageEventsDetails manageEventsDetails = manageEventsDetailsMapper.selectByPrimaryKey(list.get(i).getUuid());
            manageEventsDetails.setIsDel(1);
            manageEventsDetailsMapper.updateByPrimaryKey(manageEventsDetails);
        }
        manageEventsList.setIsDel(1);
        manageEventsListMapper.updateByPrimaryKeySelective(manageEventsList);
        return "ok";
    }

}
