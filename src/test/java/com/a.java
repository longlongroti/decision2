package com;

import com.shanxi.coal.domain.SysUser;
import liquibase.util.MD5Util;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class a {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Socket connect = new Socket();
            Thread.sleep(5000);
            try {
                connect.connect(new InetSocketAddress("172.16.65.34", 8765), 0);//建立连接
                boolean res = connect.isConnected();//通过现有方法查看连通状态
                System.out.println(new Date() + "\"http://172.16.65.34/\", 8765连接成功");//true为连通
            } catch (IOException e) {
                System.out.println(new Date() + "\"http://172.16.65.34/\", 8765连接失败");//当连不通时，直接抛异常，异常捕获即可
            } finally {
            }
        }
    }

    public static void export(ServletOutputStream out) throws Exception {
        String titles[] = {"name","username"};
        try {
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);// 列索引从0开始
                hssfCell.setCellValue(titles[i]);// 列名1
                hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
            }

            // 第五步，写入实体数据
            SysUser person1 = new SysUser();
            person1.setName("1");
            person1.setUserName("2");
            // 这里我把list当做数据库啦
            ArrayList<SysUser> list = new ArrayList<SysUser>();
            list.add(person1);

            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i + 1);
                SysUser person = list.get(i);

                // 第六步，创建单元格，并设置值
                String id = null;
                if (person.getName() != null) {
                    id = person.getName();
                }
                row.createCell(0).setCellValue(id);
                String name = "";
                if (person.getUserName() != null) {
                    name = person.getUserName();
                }
                row.createCell(1).setCellValue(name);

            }

            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }
}
