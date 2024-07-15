package com.java.train.business.controller;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestController {

    @Scheduled(cron = "0/5 * * * * ?")
    private void  Test()
    {
        System.out.println("hello Schedule");
    }
}
