package com.shanxi.coal.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.shanxi.coal.dao.AutoCodeMapper;
import com.shanxi.coal.domain.*;
import liquibase.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

public class MyUtils {
    public static FileUploaded buildFileUploaded(FileUploaded fileUploaded, String fileName, String filePath, Long fileSize, String contentType, String remark) {
        fileUploaded.setUuid(UUID.randomUUID().toString());
        fileUploaded.setFileName(fileName);
        fileUploaded.setFilePath(filePath);
        fileUploaded.setFileSize(fileSize);
        fileUploaded.setFileType(contentType);
        fileUploaded.setCreatedBy(MyUtils.getSessionUser().getUuid());
        fileUploaded.setCreatedByOrg(MyUtils.getSessionUser().getOrgId());
        fileUploaded.setRemark(remark);
        return fileUploaded;
    }

    public static String uploadFile(HttpServletRequest request, MultipartFile file, String path) {
        File destFile = makeDir(path);
        File f = new File(destFile.getAbsoluteFile() + File.separator + getFileName(file.getOriginalFilename()));
        try {
            file.transferTo(f);
            return f.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

    public static File makeDir(String path) {
        String folder = MyDateTimeUtils.strNow("yyyyMMdd");
        path = path + folder;
        File destFile = new File(path);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        return destFile;
    }

    public static String getFileName(String originalFileName) {
        String newFileName = MyDateTimeUtils.strNow("yyyyMMddHHmmssSSS");
        newFileName = newFileName + UUID.randomUUID().toString();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = newFileName + "." + suffix;
        return fileName;
    }

    public static List<String> buildCommonDataPower() {
        List<SysUserDataRole> sysUserDataRoles = getSessionUser().getSysUserDataRoles();
        List<String> list = new ArrayList<>();
        list.add(getSessionUser().getOrgId());
        for (SysUserDataRole sysUserDataRole : sysUserDataRoles) {
            SysDataRole sysDataRole = sysUserDataRole.getSysDataRole();
            if (sysDataRole != null) {
                List<SysDataRolePower> powers = sysDataRole.getPowers();
                for (SysDataRolePower sysDataRolePower : powers) {
                    list.add(sysDataRolePower.getPower());
                }
            }
        }
        return list;
    }

    public static CommonBean buildCommonWhere(CommonBean commonBean) {
        commonBean.setCreatedBy(getSessionUser().getUuid());
//        List<String> strings = buildCommonDataPower();
//        commonBean.setSupervisedOrg(strings);
        return commonBean;
    }

    public static SysUser getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        return sysUser;
    }

    public static ResponseEntity inputStream2ResponseEntity(InputStream inputStream) {
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
    }

    public static void setCookie(HttpServletResponse response, String sysUser) {
        Cookie cookie = new Cookie("sxjm_token_2020", sysUser);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Date getDateFromRange(String dateRange, String s, int i) throws ParseException {
        if (StringUtils.isEmpty(dateRange) || !dateRange.contains(s)) {
            return null;
        }
        String[] arr = dateRange.trim().split(s);
        if (arr.length != 2) {
            return null;
        }
        Date date = MyDateTimeUtils.string2Date(arr[i].trim(), "yyyy-MM-dd");
        return date;
    }

    public static void rmCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("sxjm_token_2020", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static <T> String pageInfoToJson(PageInfo<? extends T> src) {
        return objectToJson(src);
    }

    public static <T> String listToJson(List<? extends T> src) {
        return objectToJson(src);
    }

    public static String objectToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> List<T> removeDuplicate(List<T> l) {
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<T>();
        for (T t : l) {
            if (set.add(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static void setCommonBean(CommonBean commonBean) {
        commonBean.setUuid(UUID.randomUUID().toString());
        commonBean.setCreatedBy(MyUtils.getSessionUser().getUuid());
        commonBean.setCreatedByOrg(MyUtils.getSessionUser().getOrgId());
    }

    public static String beanToJson(CommonBean commonBean) {
        return objectToJson(commonBean);
    }


    public static String prettyNumber(Integer index,String pattern) {
        DecimalFormat g1 = new DecimalFormat(pattern);
        String startZeroStr = g1.format(index);
        return startZeroStr;
    }


    public static Color getRandomColor(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    public static void writeToStream(BufferedImage image, String format, OutputStream stream) throws IOException {
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static BufferedImage getCaptcha(int num1, int num2) {
        BufferedImage bi = new BufferedImage(100, 36, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        Graphics2D graphics = bi.createGraphics();
        graphics.fillRect(0, 0, 100, 36);
        Random ran1 = new Random();
        graphics.setColor(getRandomColor(ran1.nextInt(225), ran1.nextInt(225), ran1.nextInt(225)));
        graphics.setFont(new Font("粗体", Font.BOLD + Font.ITALIC, 26));
        StringBuilder sb = new StringBuilder();
        String op = "+";
        sb.append(num1);
        sb.append(op);
        sb.append(num2);
        sb.append(" = ?");
        graphics.drawString(sb.toString(), 10, 30);
        for (int i = 0; i < 15; i++) {
            graphics.drawLine(random.nextInt(50), random.nextInt(50), random
                    .nextInt(80), random.nextInt(80));
        }
        graphics.setBackground(Color.red);
        graphics.dispose();
        return bi;
    }

    public static String inputStreamToString(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = in.read()) != -1) {
            baos.write(i);
        }
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    public static String code2code(String meetingType) {
        switch (meetingType) {
            case "0":
                return "DZ";
            case "1":
                return "ZD";
            case "2":
                return "DZ";
            case "3":
                return "DS";
            case "4":
                return "JL";
            case "5":
                return "GD";
            case "6":
                return "DZ";
            case "7":
                return "QT";
            case "8":
                return "JL";
            default:
                return "";
        }
    }

    public static String code2name(String meetingType) {
        switch (meetingType) {
            case "0":
                return "党委会";
            case "1":
                return "职代会";
            case "2":
                return "党组会";
            case "3":
                return "董事会";
            case "4":
                return "经理层办公室会";
            case "5":
                return "股东会";
            case "6":
                return "党委(党组)会";
            case "7":
                return "其他";
            case "8":
                return "总经理办公会";
            default:
                return "";
        }
    }


    public static String name2code(String decisionSequence) {
        switch (decisionSequence) {
            case "党委会":
                return "DZ";
            case "党组会":
                return "DZ";
            case "总经理办公会":
                return "JL";
            case "党委（党组）会":
                return "DZ";
            case "股东会":
                return "GD";
            case "董事会":
                return "DS";
            case "经理层办公会":
                return "JL";
            case "职代会":
                return "ZD";
            case "其他":
                return "QT";
            default:
                return "";
        }
    }



    public static void insertCode(String sxbm, String a, String b, Integer c, AutoCodeMapper autoCodeMapper) {
        AutoCode autoCode = new AutoCode();
        autoCode.setRemark(sxbm);
        autoCode.setRemark1(a);
        autoCode.setRemark2(b);
        autoCode.setNumber(c);
        autoCode.setUuid(UUID.randomUUID().toString());
        autoCodeMapper.insertSelective(autoCode);
    }
}
