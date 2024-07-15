package com.java.train.member.req;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class PassengerSaveReq {



    private  Long id;

    private Long member_id;

    @NotBlank(message = "【姓名】不能为空")
    private  String name;

    @NotBlank(message = "【身份证】不能为空")
    private  String id_card;

    @NotBlank(message = "【旅客类型】不能为空")
    private  String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;
}
