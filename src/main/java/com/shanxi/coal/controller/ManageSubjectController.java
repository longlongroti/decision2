package com.shanxi.coal.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.service.CommonService;
import com.shanxi.coal.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.*;

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
    @Resource
    ManageLeaderGroupMapper leaderGroupMapper;
    @Resource
    AutoCodeMapper autoCodeMapper;
    @Resource
    CommonService commonService;
    @Resource
    ManageSubjectExecutionMapper executionMapper;
    @Resource
    ManageSubjectExecutionDutyMapper executionDutyMapper;
    @Resource
    FileUploadedMapper fileUploadedMapper;

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("meetingId") String meetingId) throws ParseException {
        List<ManageMeetingSubject> manageMeetings = subjectMapper.listByMeetingId(meetingId);
        PageInfo<ManageMeetingSubject> pageInfo = new PageInfo<ManageMeetingSubject>(manageMeetings);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @GetMapping("/add")
    public String add(@PathParam("meetingId") String meetingId, @PathParam("uuid") String uuid, Model model) {
        model.addAttribute("meetingId", meetingId);
        if (StringUtils.isNotBlank(uuid)) {
            ManageMeetingSubject manageMeetingSubject = subjectMapper.selectById(uuid);
            model.addAttribute("manageMeetingSubject", manageMeetingSubject);
        }
        return "manageSubject/add";
    }

    @GetMapping("/list")
    public String list(@PathParam("uuid") String uuid, Model model) {
        ManageMeeting manageMeeting = meetingMapper.selectByPrimaryKey(uuid);
        model.addAttribute("meetingId", uuid);
        model.addAttribute("meetingName", manageMeeting.getMeetingName());
        return "manageSubject/list";
    }

    @PostMapping("/add")
    public String add(ManageMeetingSubject manageMeetingSubject) {
        if (StringUtils.isBlank(manageMeetingSubject.getUuid())) {
            ManageMeeting manageMeeting = meetingMapper.selectByPrimaryKey(manageMeetingSubject.getMeetingId());
            if (manageMeeting != null) {
                String remark = "ytbm";
                String remark1 = manageMeeting.getSerialNum();
                AutoCode autoCode = autoCodeMapper.selectBy(remark, remark1, null);
                String code = "";
                Integer number = 1;
                if (autoCode != null) {
                    number = autoCode.getNumber() + 1;
                }
                code = remark1 + "-" + MyUtils.prettyNumber(number, "000");
                manageMeetingSubject.setSubjectCode(code);
                MyUtils.setCommonBean(manageMeetingSubject);
                manageMeetingSubject.setCreatedDate(new Date());
                subjectMapper.insert(manageMeetingSubject);
                MyUtils.insertCode(remark, remark1, null, number, autoCodeMapper);
            }
        } else {
            subjectMapper.updateByPrimaryKeySelective(manageMeetingSubject);
        }
        if (liquibase.util.StringUtils.isNotEmpty(manageMeetingSubject.getFileIds())) {
            commonService.batchUpdateFileId(manageMeetingSubject.getFileIds(), manageMeetingSubject.getUuid());
        }
        return "redirect:/manageSubject/list?uuid=" + manageMeetingSubject.getMeetingId();
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@PathParam("uuid") String uuid) {
        subjectMapper.deleteByPrimaryKey(uuid);
        itemMapper.deleteBySubjectId(uuid);
        attendanceMapper.deleteBySubjectId(uuid);
        deliberationMapper.deleteBySubjectId(uuid);
        List<ManageSubjectExecution> executionList = executionMapper.listBySubjectId(uuid);
        executionList.forEach(execution->{
            executionMapper.deleteByPrimaryKey(execution.getUuid());
            executionDutyMapper.deleteByExecutionId(execution.getUuid());
            fileUploadedMapper.deleteByCategoryId(execution.getUuid());
        });


        return "ok";
    }

    @PostMapping("/addItem")
    @ResponseBody
    public String addItem(@PathParam("subjectId") String subjectId, @PathParam("itemCode") String itemCode) {
        ManageSubjectItem item = new ManageSubjectItem();
        item.setSubjectId(subjectId);
        item.setItemCode(itemCode);
        item.setUuid(UUID.randomUUID().toString());
        itemMapper.insert(item);
        return "ok";
    }

    @PostMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(@PathParam("uuid") String uuid) {
        itemMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    @PostMapping("/listItems")
    @ResponseBody
    public String listItems(@PathParam("id") String id) {
        List<ManageSubjectItem> itemList = itemMapper.listBySubjectId(id);
        PageInfo<ManageSubjectItem> pageInfo = new PageInfo<ManageSubjectItem>(itemList);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/addAttendance")
    @ResponseBody
    public String addAttendance(@PathParam("subjectId") String subjectId, @PathParam("attendanceInfo") String attendanceInfo) {
        ManageSubjectAttendance attendance = new ManageSubjectAttendance();
        attendance.setSubjectId(subjectId);
        List<ManageLeaderGroup> leaderGroupList = leaderGroupMapper.listByUseId(attendanceInfo.split("@_@")[0]);
        String positions = "";
        if (!leaderGroupList.isEmpty()) {
            positions = leaderGroupList.get(0).getJobTitle();
        }
        attendance.setAttendanceName(attendanceInfo.split("@_@")[1]);
        attendance.setPositions(positions);
        attendance.setUuid(UUID.randomUUID().toString());
        attendanceMapper.insert(attendance);
        return "ok";
    }

    @PostMapping("/deleteAttendance")
    @ResponseBody
    public String deleteAttendance(@PathParam("uuid") String uuid) {
        attendanceMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    @PostMapping("/listAttendances")
    @ResponseBody
    public String listAttendances(@PathParam("id") String id) {
        List<ManageSubjectAttendance> attendanceList = attendanceMapper.listBySubjectId(id);
        PageInfo<ManageSubjectAttendance> pageInfo = new PageInfo<ManageSubjectAttendance>(attendanceList);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/addDeliberation")
    @ResponseBody
    public String addDeliberation(@PathParam("subjectId") String subjectId, @PathParam("deliberation") String deliberation, @PathParam("deliberationResult") String deliberationResult) {
        ManageSubjectDeliberation subjectDeliberation = new ManageSubjectDeliberation();
        subjectDeliberation.setSubjectId(subjectId);
        subjectDeliberation.setDeliberationPersonnel(deliberation);
        subjectDeliberation.setDeliberationResult(deliberationResult);
        subjectDeliberation.setUuid(UUID.randomUUID().toString());
        deliberationMapper.insert(subjectDeliberation);
        return "ok";
    }

    @PostMapping("/deleteDeliberation")
    @ResponseBody
    public String deleteDeliberation(@PathParam("uuid") String uuid) {
        deliberationMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    @PostMapping("/listDeliberation")
    @ResponseBody
    public String listDeliberation(@PathParam("id") String id) {
        List<ManageSubjectDeliberation> deliberationList = deliberationMapper.listBySubjectId(id);
        PageInfo<ManageSubjectDeliberation> pageInfo = new PageInfo<ManageSubjectDeliberation>(deliberationList);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    //组织实施
    @PostMapping("/addExecution")
    @ResponseBody
    public String addExecution(HttpServletRequest request) {
        String execution = request.getParameter("execution");
        String dutyList = request.getParameter("dutyList");
        try {
            Gson gson = new Gson();
            ManageSubjectExecution subjectExecution = gson.fromJson(execution,ManageSubjectExecution.class);
            if(liquibase.util.StringUtils.isNotEmpty(subjectExecution.getUuid())){
                executionMapper.updateByPrimaryKeySelective(subjectExecution);
            }else{
                subjectExecution.setUuid(UUID.randomUUID().toString());
                executionMapper.insert(subjectExecution);
            }

            List<ManageSubjectExecutionDuty> duties = gson.fromJson(dutyList,new TypeToken<List<ManageSubjectExecutionDuty>>(){}.getType());
            //落实责任信息保存
            executionDutyMapper.deleteByExecutionId(subjectExecution.getUuid());
            duties.forEach(duty->{
                if(liquibase.util.StringUtils.isNotEmpty(duty.getDept()) && liquibase.util.StringUtils.isNotEmpty(duty.getName()) ){
                    duty.setExecutionId(subjectExecution.getUuid());
                    duty.setUuid(UUID.randomUUID().toString());
                    executionDutyMapper.insert(duty);
                }
            });
            //文件上传
            if (liquibase.util.StringUtils.isNotEmpty(subjectExecution.getFileIds())) {
                commonService.batchUpdateFileId(subjectExecution.getFileIds(), subjectExecution.getUuid());
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @PostMapping("/deleteExecution")
    @ResponseBody
    public String deleteExecution(@PathParam("uuid") String uuid) {
        executionMapper.deleteByPrimaryKey(uuid);
        executionDutyMapper.deleteByExecutionId(uuid);
        fileUploadedMapper.deleteByCategoryId(uuid);
        return "ok";
    }

    @PostMapping("/listExecution")
    @ResponseBody
    public String listExecution(@PathParam("id") String id) {
        List<ManageSubjectExecution> executionList = executionMapper.listBySubjectId(id);
        PageInfo<ManageSubjectExecution> pageInfo = new PageInfo<>(executionList);
        return MyUtils.pageInfoToJson(pageInfo);
    }
    @PostMapping("/getExecutionBySubjectId")
    @ResponseBody
    public Map<String,Object> getExecutionBySubjectId(HttpServletRequest request){
        Map<String,Object> dataMap = new HashMap<>();
        String subjectId = request.getParameter("subjectId");
        ManageSubjectExecution execution = executionMapper.getExecutionBySubjectId(subjectId);
        dataMap.put("data",execution);
        if(execution == null){
            dataMap.put("flag",0);
        }else{
            dataMap.put("flag",1);
        }
        return dataMap;
    }


}
