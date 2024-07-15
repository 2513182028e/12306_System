package com.java.train.business.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class TestJob implements Job {


    private  final Logger log=LoggerFactory.getLogger(TestJob.class);

    static  int count=0;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            System.out.println("定时任务测试开启,次数为"+count);
    }
}
