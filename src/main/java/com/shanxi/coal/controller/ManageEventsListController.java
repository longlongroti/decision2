package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.service.CommonService;
import com.shanxi.coal.utils.MyDateTimeUtils;
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
    @Resource
    ManageEventsDetailsItemMapper manageEventsDetailsItemMapper;
    @Resource
    AutoCodeMapper autoCodeMapper;
    @Resource
    CommonService commonService;

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
    public String add(ManageEventsList manageEventsList, @RequestParam("eventId") String eventId, @RequestParam("listName") String listName,
                      @RequestParam("versionNumber") String versionNumber, @RequestParam("startTime") String startTime,
                      @RequestParam("endTime") String endTime, @RequestParam("remark") String remark, @RequestParam("fileIds") String fileIds) {
        manageEventsList.setListName(listName);
        manageEventsList.setVersionNumber(versionNumber);
        manageEventsList.setStartTime(startTime);
        manageEventsList.setEndTime(endTime);
        manageEventsList.setRemark(remark);
        manageEventsList.setFileIds(fileIds);
        MyUtils.setCommonBean(manageEventsList);
        manageEventsListMapper.insertSelective(manageEventsList);
        if (StringUtils.isNotEmpty(manageEventsList.getFileIds())) {
            commonService.batchUpdateFileId(manageEventsList.getFileIds(), manageEventsList.getUuid());
        }
        return insertDetail(manageEventsList.getUuid(), eventId);
    }

    @PutMapping("/add")
    public String update(ManageEventsList manageEventsList,
                         @RequestParam(value = "mainUuid", required = false) String mainUuid,
                         @RequestParam(value = "eventId", required = false) String eventId,
                         @RequestParam("listName") String listName,
                         @RequestParam("versionNumber") String versionNumber,
                         @RequestParam("startTime") String startTime,
                         @RequestParam("endTime") String endTime,
                         @RequestParam("remark") String remark,
                         @RequestParam("fileIds") String fileIds) {
        manageEventsList.setUuid(mainUuid);
        manageEventsList.setListName(listName);
        manageEventsList.setVersionNumber(versionNumber);
        manageEventsList.setStartTime(startTime);
        manageEventsList.setEndTime(endTime);
        manageEventsList.setRemark(remark);
        manageEventsList.setFileIds(fileIds);
        manageEventsListMapper.updateByPrimaryKeySelective(manageEventsList);
        if (StringUtils.isNotEmpty(manageEventsList.getFileIds())) {
            commonService.batchUpdateFileId(manageEventsList.getFileIds(), manageEventsList.getUuid());
        }
        String mainUUid = manageEventsList.getUuid();
        manageEventsDetailsMapper.deleteByMainId(mainUUid);
        return insertDetail(manageEventsList.getUuid(), eventId);
    }

    private String insertDetail(String parentId, String eventId) {
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
                       @RequestParam("checkYear") String checkYear,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageEventsList where = new ManageEventsList();
        MyUtils.buildCommonWhere(where);
        where.setVersionNumber(StringUtils.isNotEmpty(checkYear) ? checkYear : null);
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

    @PostMapping("/listDetailItems")
    @ResponseBody
    public String listDetailItems(@RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("id") String id) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        List<ManageEventsDetailItem> manageEventsDetailItems = manageEventsDetailsItemMapper.listByParentId(id);
        PageInfo<ManageEventsDetailItem> pageInfo = new PageInfo<ManageEventsDetailItem>(manageEventsDetailItems);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/listAllDetailItems")
    @ResponseBody
    public String listAllDetailItems() throws ParseException {
        List<ManageEventsDetailItem> manageEventsDetailItems = manageEventsDetailsItemMapper.listAllPending(MyUtils.getSessionUser().getUuid());
        return MyUtils.objectToJson(manageEventsDetailItems);
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
        manageEventsDetailsMapper.deleteByMainId(uuid);
        manageEventsListMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    @PostMapping("/deleteDetailItem")
    @ResponseBody
    public String deleteDetailItem(@PathParam("uuid") String uuid) {
        manageEventsDetailsItemMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    @PostMapping("/addDetailItem")
    @ResponseBody
    public String addDetailItem(@PathParam("itemid") String itemid,
                                @PathParam("eventName") String eventName,
                                @PathParam("seq1") String seq1,
                                @PathParam("seq2") String seq2,
                                @PathParam("seq3") String seq3,
                                @PathParam("parentId") String parentId,
                                @PathParam("islegal") String islegal) {
        ManageEventsDetails manageEventsDetails = manageEventsDetailsMapper.selectByPrimaryKey(itemid);
        if (manageEventsDetails != null) {
            DicEventsCatalog dicEventsCatalog = dicEventsCatalogMapper.selectByPrimaryKey(manageEventsDetails.getEventId());
            if (dicEventsCatalog != null) {
                ManageEventsDetailItem manageEventsDetailItem = new ManageEventsDetailItem();
                manageEventsDetailItem.setUuid(UUID.randomUUID().toString());
                manageEventsDetailItem.setDecisionSequence(seq1);
                manageEventsDetailItem.setCreatedBy(MyUtils.getSessionUser().getUuid());
                manageEventsDetailItem.setDecisionSequence2(seq2);
                manageEventsDetailItem.setDecisionSequence3(seq3);
                manageEventsDetailItem.setEventId(itemid);
                AutoCode autoCode = autoCodeMapper.selectBy("sxbm", dicEventsCatalog.getCatalogCode(), MyDateTimeUtils.strNow("yyyyMM"));
                String code = "";
                String a = "";
                String b = "";
                Integer c = 1;
                if (autoCode == null) {
                    a = dicEventsCatalog.getCatalogCode();
                    b = MyDateTimeUtils.strNow("yyyyMM");
                    c = 1;
                } else {
                    a = autoCode.getRemark1();
                    b = autoCode.getRemark2();
                    c = autoCode.getNumber() + 1;
                }
                code = a + "-" + b + "-" + MyUtils.prettyNumber(c, "000000");
                manageEventsDetailItem.setEventCode(code);
                manageEventsDetailItem.setEventName(eventName);
                manageEventsDetailItem.setIsLegalReview(islegal);
                manageEventsDetailItem.setParentId(parentId);
                manageEventsDetailsItemMapper.insertSelective(manageEventsDetailItem);
                MyUtils.insertCode("sxbm", a, b, c, autoCodeMapper);
                return "ok";
            }
        }
        return "error";
    }


}
