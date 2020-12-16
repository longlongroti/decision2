package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.dao.FileUploadedMapper;
import com.shanxi.coal.domain.FileUploaded;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;

import static com.shanxi.coal.utils.MyUtils.beanToJson;
import static com.shanxi.coal.utils.MyUtils.uploadFile;

@Controller
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    FileUploadedMapper fileUploadedMapper;
    @Resource
    MyProperties myProperties;

    @GetMapping("/detail")
    @ResponseBody
    public String list(@RequestParam("uuid") String uuid, Model model) {
        FileUploaded fileUploaded = fileUploadedMapper.selectByPrimaryKey(uuid);
        return beanToJson(fileUploaded);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("type") String type) {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        String filePath = uploadFile(request, file, myProperties.getFileUploadedPath());
        if (StringUtils.isNotEmpty(filePath)) {
            FileUploaded fileUploaded = new FileUploaded();
            fileUploaded.setFileCategory(type);
            FileUploaded fileUploaded1 = MyUtils.buildFileUploaded(fileUploaded, file.getOriginalFilename(), filePath, file.getSize(), file.getContentType(), "0");
            fileUploadedMapper.insertSelective(fileUploaded1);
            return MyUtils.objectToJson(fileUploaded1.getUuid());
        }
        return null;
    }


    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@PathParam("uuid") String uuid) throws IOException {
        FileUploaded fileUploaded = fileUploadedMapper.selectByPrimaryKey(uuid);
        FileSystemResource file = new FileSystemResource(fileUploaded.getFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", java.net.URLEncoder.encode(fileUploaded.getFileName(), "UTF-8")));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType(fileUploaded.getFileType()))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("uuid") String uuid) {
        fileUploadedMapper.deleteByPrimaryKey(uuid);//todo update isDel
        return "ok";
    }
}
