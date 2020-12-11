package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manageMeeting")
public class ManageMeetingController {

    @Resource
    ManageMeetingMapper manageMeetingMapper;
    @Resource
    ManageLeaderMapper manageLeaderMapper;
    @Resource
    ManageMeetingAttendeeMapper manageMeetingAttendeeMapper;

    @GetMapping("/go")
    public String go() {
        return "manageMeeting/list";
    }

    @GetMapping("/goadd")
    public String goAdd(Model model) {
        List<ManageLeader> manageLeaders = manageLeaderMapper.getLeaders();
        model.addAttribute("leadersSelection", manageLeaders);
        model.addAttribute("attendeesSelection", manageLeaders);
        model.addAttribute("user", MyUtils.getSessionUser());
        return "manageMeeting/add";
    }

    @GetMapping("/goedit")
    public String goEdit(@PathParam("uuid") String uuid, Model model) {
        if (StringUtils.isEmpty(uuid)) {
            return "redirect:/manageMeeting/go";
        }
        List<ManageLeader> manageLeaders = manageLeaderMapper.getLeaders();
        model.addAttribute("leadersSelection", manageLeaders);
        model.addAttribute("attendeesSelection", manageLeaders);
        ManageMeeting manageMeeting = manageMeetingMapper.selectAttendeesExtra(uuid);
        if (manageMeeting == null) { return "redirect:/manageMeeting/go"; }
        model.addAttribute("manageMeetingSel", manageMeeting);
        return "manageMeeting/add";
    }

    @PostMapping("/add")
    public String add(ManageMeeting manageMeeting) {
        MyUtils.setCommonBean(manageMeeting);
        manageMeetingMapper.insertSelective(manageMeeting);
        batchInsertAttendee(manageMeeting);
        return "manageMeeting/list";
    }

    private void batchInsertAttendee(ManageMeeting manageMeeting) {
        if (StringUtils.isEmpty(manageMeeting.getAttendees())) {
            return;
        }
        String[] arr = manageMeeting.getAttendees().split(",");
        if (arr.length > 0) {
            List<ManageMeetingAttendee> list = new ArrayList<>(arr.length);
            for (String s : arr) {
                ManageMeetingAttendee manageMeetingAttendee = new ManageMeetingAttendee();
                manageMeetingAttendee.setMeetingId(manageMeeting.getUuid());
                manageMeetingAttendee.setLeaderId(s);
                manageMeetingAttendee.setUuid(UUID.randomUUID().toString());
                list.add(manageMeetingAttendee);
            }
            manageMeetingAttendeeMapper.insertBatch(list);
        }
    }

    @PutMapping("/add")
    public String update(ManageMeeting manageMeeting) {
        manageMeetingMapper.updateByPrimaryKeySelective(manageMeeting);
        manageMeetingAttendeeMapper.deleteByMeetingId(manageMeeting.getUuid());
        batchInsertAttendee(manageMeeting);
        return "manageMeeting/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageMeeting where = new ManageMeeting();
        where.setStatus(0);
        List<ManageMeeting> manageMeetings = manageMeetingMapper.getList(where);
        PageInfo<ManageMeeting> pageInfo = new PageInfo<ManageMeeting>(manageMeetings);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        ManageMeeting manageMeeting = manageMeetingMapper.selectByPrimaryKey(uuid);
        if (manageMeeting != null){
            manageMeeting.setIsDel(1);
            manageMeetingMapper.updateByPrimaryKeySelective(manageMeeting);
            manageMeetingAttendeeMapper.deleteByMeetingId(manageMeeting.getUuid());
        }
        return "ok";
    }

}
