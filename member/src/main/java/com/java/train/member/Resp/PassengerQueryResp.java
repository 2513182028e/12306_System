package com.java.train.member.Resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class PassengerQueryResp {



    @JsonSerialize(using = ToStringSerializer.class)  // 将Long类型的字段变为String类型
    private  Long id;

    @JsonSerialize(using = ToStringSerializer.class)   // 将Long类型的字段变为String类型
    private Long member_id;


    private  String name;


    private  String id_card;


    private  String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;
}
