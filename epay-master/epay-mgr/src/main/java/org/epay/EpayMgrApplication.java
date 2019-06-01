package org.epay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableTransactionManagement
//@EnableEurekaClient
//@SpringCloudApplication
public class EpayMgrApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EpayMgrApplication.class, args);
        /*ConfigurableApplicationContext ctx = SpringApplication.run(EpayMgrApplication.class, args);
        MyConf myconf = (MyConf) ctx.getBean("myConf");
        myconf.show();
        ctx.close();*/
    }

    /**
     * 读取配置文件can work
     * GaoLiang
     */
    /*public static void main(String[] args) {
        // 获取 Spring Boot 上下文
        ConfigurableApplicationContext ctx = SpringApplication.run(EpayMgrApplication.class, args);
        // ctx.getEnvironment(); // 获取 边境变量
        System.out.println("===========================================");
        //获取字符串
        System.out.println(ctx.getEnvironment().getProperty("web.path"));
        System.out.println("===========================================");

    }*/

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.listeners();
        return application.sources(applicationClass);
    }

    private static Class<EpayMgrApplication> applicationClass = EpayMgrApplication.class;

}