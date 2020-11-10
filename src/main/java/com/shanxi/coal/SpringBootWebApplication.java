package com.shanxi.coal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/*@SpringBootApplication 说明这个类是SpringBoot的住配置类
* ---@SpringBootConfiguration, 表示这是一个springBoot的配置类，@Configuration 配置类
* ---@EnableAutoConfiguration, 开启自动配置，@AutoConfigurationPackage自动配置包，@Import给容器中导入组件
* --- --- AutoConfigurationPackages.registerBeanDefinitions()  将主配置类的所在包及下面所有子包里面的所有组件扫描到spring容器
* --- ---@Import 给容器中导入EnableAutoConfigurationImportSelector.selectImports() 将所有需要导入的组件添加到spring容器中
* 会把所有的自动配置类xxxxAutoConfiguration导入进来，并且配置好
* SpringFactoriesLoader.loadFactoryNames()  从META-INF/spring.factories中获取EnableAutoConfiguration 指定的值
* 将这些值作为自动配置类导入到容器中，自动配置类生效
*
*
* springboot 默认使用嵌入的servlet(tomcat)
* */
@SpringBootApplication
/*包含了enableautoconfiguration 和 componentscan包*/
@MapperScan("com.shanxi.coal.dao")
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.shanxi.coal")/*若没有指定自动扫描所在位置以下的包*/
public class SpringBootWebApplication {
    public static void main(String[] args) {
        try {
            /* 简写 SpringApplication.run(SpringBootWebApplication.class, args);*/
            SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
            /*在console中打印自定义banner*/
            app.setBannerMode(Banner.Mode.CONSOLE);
            app.run(args);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
