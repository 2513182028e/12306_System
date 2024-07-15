package com.java.train.business.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class DailyTrainSeat {
    private Long id;

    private Date date;

    private String trainCode;

    private Integer carriageIndex;

    private String row;

    private String col;

    private String seatType;

    private Integer carriageSeatIndex;

    private String sell;

    private Date createTime;

    private Date updateTime;


}