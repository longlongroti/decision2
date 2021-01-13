package com.shanxi.coal.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.ManageLeaderGroupMapper;
import com.shanxi.coal.dao.ManageLeaderMapper;
import com.shanxi.coal.domain.ManageLeader;
import com.shanxi.coal.domain.ManageLeaderGroup;
import com.shanxi.coal.excel.LeaderHandler;
import com.shanxi.coal.excel.LeaderTypeHandler;
import com.shanxi.coal.excel.ManageLeaderGroupListener;
import com.shanxi.coal.excel.ManageLeaderListener;
import com.shanxi.coal.service.CommonService;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URLEncoder;
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
    @Resource
    CommonService commonService;

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
    public String add(ManageLeader manageLeader,
                      @RequestParam(value = "leaderGroup", required = false) String leaderGroup,
                      @RequestParam(value = "jobTitle", required = false) String jobTitle,
                      @RequestParam(value = "officeStartDate", required = false) String officeStartDate,
                      @RequestParam(value = "officeEndDate", required = false) String officeEndDate, Model model1) {
        String[] leaderGroups = null;
        if (StringUtils.isNotEmpty(leaderGroup)) {
            leaderGroups = leaderGroup.split(",");
        }
        String[] jobTitles = null;
        if (StringUtils.isNotEmpty(jobTitle)) {
            jobTitles = jobTitle.split(",");
        }
        String[] officeStartDates = null;
        if (StringUtils.isNotEmpty(officeStartDate)) {
            officeStartDates = officeStartDate.split(",");
        }
        String[] officeEndDates = null;
        if (StringUtils.isNotEmpty(officeEndDate)) {
            officeEndDates = officeEndDate.split(",");
        }
        MyUtils.setCommonBean(manageLeader);
        manageLeaderMapper.insertSelective(manageLeader);
        if (StringUtils.isNotEmpty(manageLeader.getFileIds())) {
            commonService.batchUpdateFileId(manageLeader.getFileIds(), manageLeader.getUuid());
        }
        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageLeader.getUuid());
    }

    @PutMapping("/add")
    public String update(ManageLeader manageLeader,
                         @RequestParam(value = "leaderGroup", required = false) String leaderGroup,
                         @RequestParam(value = "jobTitle", required = false) String jobTitle,
                         @RequestParam(value = "officeStartDate", required = false) String officeStartDate,
                         @RequestParam(value = "officeEndDate", required = false) String officeEndDate) {
        String[] leaderGroups = null;
        if (StringUtils.isNotEmpty(leaderGroup)) {
            leaderGroups = leaderGroup.split(",");
        }
        String[] jobTitles = null;
        if (StringUtils.isNotEmpty(jobTitle)) {
            jobTitles = jobTitle.split(",");
        }
        String[] officeStartDates = null;
        if (StringUtils.isNotEmpty(officeStartDate)) {
            officeStartDates = officeStartDate.split(",");
        }
        String[] officeEndDates = officeEndDate.split(",");
        if (StringUtils.isNotEmpty(officeEndDate)) {
            officeEndDates = officeEndDate.split(",");
        }
        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
        if (StringUtils.isNotEmpty(manageLeader.getFileIds())) {
            commonService.batchUpdateFileId(manageLeader.getFileIds(), manageLeader.getUuid());
        }
        String mainUUid = manageLeader.getUuid();
        manageLeaderGroupMapper.deleteByParentId(mainUUid);
        return insertDetail(leaderGroups, jobTitles, officeStartDates, officeEndDates, manageLeader.getUuid());
    }

    private String insertDetail(String[] leaderGroups, String[] jobTitles, String[] officeStartDates,
                                String[] officeEndDates, String mainUUid) {
        if(leaderGroups!=null) {
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
        }
        return "redirect:/manageLeader/go";
    }

    public String checkNullAndReturn(String[] array, int idx) {
        if(array ==null){
            return "";
        }
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
                       @RequestParam("name") String name,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        ManageLeader where = new ManageLeader();
        MyUtils.buildCommonWhere(where);
        where.setStatus(0);
        where.setLeaderName(StringUtils.isNotEmpty(name) ? name : null);
        List<ManageLeader> manageLeader = manageLeaderMapper.getList(where);
        PageInfo<ManageLeader> pageInfo = new PageInfo<ManageLeader>(manageLeader);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/leaderList")
    @ResponseBody
    public List<ManageLeader> leaderList() {
        List<ManageLeader> manageLeader = manageLeaderMapper.getLeaders();
        return manageLeader;
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

    @GetMapping("/exportExcel.xlsx")
    public void exportExcel(HttpServletResponse response) throws ParseException, IOException {
        String fileName = "报表";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        ManageLeader where = new ManageLeader();
        where.setIsDel(0);
        List<ManageLeader> orgBaseInfos = manageLeaderMapper.getList(where);
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "领导信息").registerWriteHandler(new LeaderHandler()).head(ManageLeader.class).build();
        excelWriter.write(orgBaseInfos, writeSheet);
        List<ManageLeaderGroup> manageLeaderGroups = manageLeaderGroupMapper.list();
        writeSheet = EasyExcel.writerSheet(1, "所属类别").registerWriteHandler(new LeaderTypeHandler()).head(ManageLeaderGroup.class).build();
        excelWriter.write(manageLeaderGroups, writeSheet);
        excelWriter.finish();
    }

    @PostMapping(value = "/importExcel.xlsx")
    public @ResponseBody
    String importPicFile1(@RequestParam MultipartFile file, HttpServletRequest request) {
        try {
            ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
            ReadSheet readSheet0 =
                    EasyExcel.readSheet(0).head(ManageLeader.class).registerReadListener(new ManageLeaderListener(manageLeaderMapper)).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(ManageLeaderGroup.class).registerReadListener(new ManageLeaderGroupListener(manageLeaderGroupMapper)).build();
            excelReader.read(readSheet0, readSheet2);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            return MyUtils.objectToJson("成功");
        } catch (Exception e) {
            return MyUtils.objectToJson("文件格式不正确，请参考导出的文件格式");
        }
    }


}
