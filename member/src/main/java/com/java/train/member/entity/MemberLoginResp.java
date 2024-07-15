package com.java.train.member.entity;


import lombok.Data;

@Data
public class MemberLoginResp {

    private Long id;

    private String mobile;

    private String token;


}
