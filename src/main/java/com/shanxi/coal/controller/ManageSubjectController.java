package com.shanxi.coal.controller;

import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.ManageMeeting;
import com.shanxi.coal.domain.ManageMeetingSubject;
import com.shanxi.coal.domain.ManageSubjectItem;
import com.shanxi.coal.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 议题
 */
@Controller
@RequestMapping("/manageSubject")
public class ManageSubjectController {

    @Resource
    ManageMeetingSubjectMapper subjectMapper;
    @Resource
    ManageMeetingMapper meetingMapper;
    @Resource
    ManageSubjectItemMapper itemMapper;
    @Resource
    ManageSubjectAttendanceMapper attendanceMapper;
    @Resource
    ManageSubjectDeliberationMapper deliberationMapper;


    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("meetingId") String meetingId) throws ParseException {
        List<ManageMeetingSubject> manageMeetings = subjectMapper.listByMeetingId(meetingId);
        PageInfo<ManageMeetingSubject> pageInfo = new PageInfo<ManageMeetingSubject>(manageMeetings);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @GetMapping("/add")
    public String add(@PathParam("meetingId") String meetingId,@PathParam("uuid") String uuid, Model model) {
        model.addAttribute("meetingId",meetingId);
        if(StringUtils.isNotBlank(uuid)){
            ManageMeetingSubject  manageMeetingSubject = subjectMapper.selectByPrimaryKey(uuid);
            model.addAttribute("manageMeetingSubject",manageMeetingSubject);
        }
        return "manageSubject/add";
    }
    @GetMapping("/list")
    public String list(@PathParam("uuid") String uuid,Model model) {
        ManageMeeting manageMeeting = meetingMapper.selectByPrimaryKey(uuid);
        model.addAttribute("meetingId",uuid);
        model.addAttribute("meetingName",manageMeeting.getMeetingName());
        return "manageSubject/list";
    }

    @PostMapping("/add")
    public String add(ManageMeetingSubject  manageMeetingSubject) {
        if(StringUtils.isBlank(manageMeetingSubject.getUuid())){
            MyUtils.setCommonBean(manageMeetingSubject);
            manageMeetingSubject.setCreatedDate(new Date());
            subjectMapper.insert(manageMeetingSubject);
        }else {

            subjectMapper.updateByPrimaryKeySelective(manageMeetingSubject);
        }
        return "redirect:/manageSubject/list?uuid="+manageMeetingSubject.getMeetingId();
    }


    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid){
        subjectMapper.deleteByPrimaryKey(uuid);


        return "ok";
    }

    @PostMapping("/addItem")
    @ResponseBody
    public String addItem(@PathParam("subjectId") String subjectId,@PathParam("itemCode") String itemCode){
        ManageSubjectItem item = new ManageSubjectItem();
        item.setSubjectId(subjectId);
        item.setItemCode(itemCode);
        item.setUuid(UUID.randomUUID().toString());
        itemMapper.insert(item);
        return "ok";
    }
    @PostMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(@PathParam("uuid") String uuid){
        itemMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }
    @PostMapping("/listItems")
    @ResponseBody
    public String listItems(@PathParam("id") String id){
        List<ManageSubjectItem> itemList = itemMapper.listBySubjectId(id);
        PageInfo<ManageSubjectItem> pageInfo = new PageInfo<ManageSubjectItem>(itemList);
        return MyUtils.pageInfoToJson(pageInfo);
    }













}
