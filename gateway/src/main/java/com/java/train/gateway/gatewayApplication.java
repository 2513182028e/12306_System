package com.java.train.gateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class gatewayApplication {

    private static  final Logger Log= LoggerFactory.getLogger(gatewayApplication.class);
    public static void main(String[] args) {

        //SpringApplication.run(MemberApplication.class, args);
        SpringApplication app=new SpringApplication(gatewayApplication.class);
        Environment env=app.run(args).getEnvironment();
        Log.info("启动成功");
        Log.info("网关地址:\t http://127.0.0.1:{}{}/hello",env.getProperty("server.port"),env.getProperty("server.servlet.context-path"));
    }

}