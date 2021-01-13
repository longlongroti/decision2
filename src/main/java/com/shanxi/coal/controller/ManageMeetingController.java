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
    @Resource
    CommonService commonService;
    @Resource
    AutoCodeMapper autoCodeMapper;

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
        if (manageMeeting == null) {
            return "redirect:/manageMeeting/go";
        }
        model.addAttribute("manageMeetingSel", manageMeeting);
        return "manageMeeting/add";
    }

    @PostMapping("/add")
    public String add(ManageMeeting manageMeeting) {
        String a = MyUtils.code2code(manageMeeting.getMeetingType());
        MyUtils.setCommonBean(manageMeeting);
        AutoCode autoCode = autoCodeMapper.selectBy("hybm", a, MyDateTimeUtils.strNow("yyyy"));
        String code = "";
        String b = "";
        Integer c = 1;
        if (autoCode == null) {
            b = MyDateTimeUtils.strNow("yyyy");
            c = 1;
        } else {
            b = autoCode.getRemark2();
            c = autoCode.getNumber() + 1;
        }
        code = a + b + MyUtils.prettyNumber(c, "0000");
        manageMeeting.setSerialNum(code);
        manageMeetingMapper.insertSelective(manageMeeting);
        if (StringUtils.isNotEmpty(manageMeeting.getFileIds())) {
            commonService.batchUpdateFileId(manageMeeting.getFileIds(), manageMeeting.getUuid());
        }
        batchInsertAttendee(manageMeeting);
        MyUtils.insertCode("hybm", a, b, c, autoCodeMapper);
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
        if (StringUtils.isNotEmpty(manageMeeting.getFileIds())) {
            commonService.batchUpdateFileId(manageMeeting.getFileIds(), manageMeeting.getUuid());
        }
        manageMeetingAttendeeMapper.deleteByMeetingId(manageMeeting.getUuid());
        batchInsertAttendee(manageMeeting);
        return "manageMeeting/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("meetingType") String meetingType,
                       @RequestParam("name") String name,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageMeeting where = new ManageMeeting();
        MyUtils.buildCommonWhere(where);
        where.setMeetingName(StringUtils.isNotEmpty(name) ? name : null);
        where.setMeetingType(StringUtils.isNotEmpty(meetingType) ? meetingType : null);
        List<ManageMeeting> manageMeetings = manageMeetingMapper.getList(where);
        PageInfo<ManageMeeting> pageInfo = new PageInfo<ManageMeeting>(manageMeetings);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        ManageMeeting manageMeeting = manageMeetingMapper.selectByPrimaryKey(uuid);
        if (manageMeeting != null) {
            manageMeeting.setIsDel(1);
            manageMeetingMapper.updateByPrimaryKeySelective(manageMeeting);
            manageMeetingAttendeeMapper.deleteByMeetingId(manageMeeting.getUuid());
        }
        return "ok";
    }


}
