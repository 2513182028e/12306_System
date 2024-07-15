package com.java.train.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("member")
public class Member {

    @TableId("id")
    private  Long id;

    @TableField("mobile")
    private String mobile;

}
