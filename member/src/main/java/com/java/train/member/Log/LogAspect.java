package com.java.train.member.Log;


import org.springframework.stereotype.Component;


@Component

public class LogAspect {
    public LogAspect()
    {
        System.out.println("LogAspect");
    }
    //private final static Logger Log= LoggerFactory.getLogger(LogAspect.class);



}
