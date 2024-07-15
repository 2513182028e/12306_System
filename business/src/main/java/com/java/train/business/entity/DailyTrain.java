package com.java.train.business.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class DailyTrain {

    private Long id;

    private Date date;

    private String code;

    private String type;

    private String start;

    private String startPinyin;

    private Date startTime;

    private String end;

    private String endPinyin;

    private Date endTime;

    private Date createTime;

    private Date updateTime;


}