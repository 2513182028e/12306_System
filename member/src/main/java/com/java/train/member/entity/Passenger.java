package com.java.train.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("passenger")
public class Passenger {

    @TableId("id")
    private  Long id;
    @TableField("member_id")
    private Long member_id;
    @TableField("name")
    private  String name;
    @TableField("id_card")
    private  String id_card;
    @TableField("type")
    private  String type;
    @TableField("create_time")
    private Date create_time;
    @TableField("update_time")
    private Date update_time;
}
