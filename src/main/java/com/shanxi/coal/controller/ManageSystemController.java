package com.shanxi.coal.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.ManageSystemItemsMapper;
import com.shanxi.coal.dao.ManageSystemMapper;
import com.shanxi.coal.domain.ManageLeader;
import com.shanxi.coal.domain.ManageLeaderGroup;
import com.shanxi.coal.domain.ManageSystem;
import com.shanxi.coal.domain.ManageSystemItems;
import com.shanxi.coal.excel.*;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manageSystem")
public class ManageSystemController {

    @Resource
    ManageSystemMapper manageSystemMapper;
    @Resource
    ManageSystemItemsMapper manageSystemItemsMapper;
    @Resource
    CommonService commonService;

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
    public String add(ManageSystem manageSystem, Model model) {
        MyUtils.setCommonBean(manageSystem);
        manageSystemMapper.insertSelective(manageSystem);
        if (StringUtils.isNotEmpty(manageSystem.getFileIds())) {
            commonService.batchUpdateFileId(manageSystem.getFileIds(), manageSystem.getUuid());
        }
        return "redirect:/manageSystem/go";
    }

    @PutMapping("/add")
    public String update(ManageSystem manageSystem) {
        manageSystemMapper.updateByPrimaryKeySelective(manageSystem);
        if (StringUtils.isNotEmpty(manageSystem.getFileIds())) {
            commonService.batchUpdateFileId(manageSystem.getFileIds(), manageSystem.getUuid());
        }
        manageSystemItemsMapper.deleteByParentId(manageSystem.getUuid());
        return "redirect:/manageSystem/go";
    }

    @PostMapping("/addDetailItem")
    @ResponseBody
    public String addDetailItem(@RequestParam("itemsName") String itemsName,
                                @RequestParam("votingFormula") String votingFormula,
                                @RequestParam("peopleCount") String peopleCount,
                                @RequestParam("mainUUid") String mainUUID) {
        insertDetail(itemsName, votingFormula, peopleCount, mainUUID);
        return "ok";
    }

    @PostMapping("/listDetailItems")
    @ResponseBody
    public String listDetailItems(@RequestParam("pageNumber") Integer pageNumber,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("id") String id) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        List<ManageSystemItems> manageSystemItems = manageSystemItemsMapper.listByUseId(id);
        PageInfo<ManageSystemItems> pageInfo = new PageInfo<ManageSystemItems>(manageSystemItems);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @PostMapping("/deleteDetailItem")
    @ResponseBody
    public String deleteDetailItem(@PathParam("uuid") String uuid) {
        manageSystemItemsMapper.deleteByPrimaryKey(uuid);
        return "ok";
    }

    private void insertDetail(String itemsNames, String votingFormulas, String peopleCounts, String mainUUid) {
        ManageSystemItems manageSystemItems = new ManageSystemItems();
        manageSystemItems.setUuid(UUID.randomUUID().toString());
        manageSystemItems.setSystemParentId(mainUUid);
        manageSystemItems.setItemsName(itemsNames);
        manageSystemItems.setVotingFormula(votingFormulas);
        manageSystemItems.setPeopleCount(peopleCounts);
        manageSystemItemsMapper.insertSelective(manageSystemItems);
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
        where.setSystemType(StringUtils.isNotEmpty(systemType) ? systemType : null);
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

    @GetMapping("/exportExcel.xlsx")
    public void exportExcel(HttpServletResponse response) throws ParseException, IOException {
        String fileName = "报表";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        ManageSystem where = new ManageSystem();
        where.setIsDel(0);
        List<ManageSystem> manageSystems = manageSystemMapper.getList(where);
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "制度信息").registerWriteHandler(new ManageSystemHandler()).head(ManageSystem.class).build();
        excelWriter.write(manageSystems, writeSheet);
        List<ManageSystemItems> manageSystemItems = manageSystemItemsMapper.list();
        writeSheet = EasyExcel.writerSheet(1, "所属类别").registerWriteHandler(new ManageSystemItemsHandler()).head(ManageSystemItems.class).build();
        excelWriter.write(manageSystemItems, writeSheet);
        excelWriter.finish();
    }

    @PostMapping(value = "/importExcel.xlsx")
    public @ResponseBody
    String importPicFile1(@RequestParam MultipartFile file, HttpServletRequest request) {
        try {
            ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
            ReadSheet readSheet0 =
                    EasyExcel.readSheet(0).head(ManageSystem.class).registerReadListener(new ManageSystemListener(manageSystemMapper)).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(ManageSystemItems.class).registerReadListener(new ManageSystemItemsListener(manageSystemItemsMapper)).build();
            excelReader.read(readSheet0, readSheet2);
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
            return MyUtils.objectToJson("成功");
        } catch (Exception e) {
            return MyUtils.objectToJson("文件格式不正确，请参考导出的文件格式");
        }
    }
}
