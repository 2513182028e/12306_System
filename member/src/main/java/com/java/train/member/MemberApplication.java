package com.java.train.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EnableDiscoveryClient
public class MemberApplication {

    private static  final Logger Log= LoggerFactory.getLogger(MemberApplication.class);
    public static void main(String[] args) {

        //SpringApplication.run(MemberApplication.class, args);
        SpringApplication   app=new SpringApplication(MemberApplication.class);
        Environment env=app.run(args).getEnvironment();
        Log.info("启动成功");
        Log.info("地址:\t http://127.0.0.1:{}{}/hello:{}",env.getProperty("server.port"),env.getProperty("server.servlet.context-path"),
                env.getProperty("T.nacos"));
    }

}
