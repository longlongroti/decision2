package com;

import com.shanxi.coal.SpringBootWebApplication;
import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.config.MyProperties;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = SpringBootWebApplication.class)
public class Test2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int num = sc.nextInt();

        String str1 = num + "";

        StringBuilder str2 = new StringBuilder(str1);
        str2.reverse();
        int count = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                System.out.println(str1 + "不是回文数");
                break;
            } else {
                count++;
            }
        }
        if (count == str1.length()) {
            System.out.println(str1 + "是回文数");
        }
    }

}

