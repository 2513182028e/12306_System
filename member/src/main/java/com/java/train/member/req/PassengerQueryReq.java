package com.java.train.member.req;

import com.java.train.common.req.PageReq;
import lombok.Data;


@Data
public class PassengerQueryReq extends PageReq {

    private Long memberId;



}
