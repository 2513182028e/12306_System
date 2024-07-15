package com.java.train.member.controller;


import com.java.train.common.resp.CommonResp;
import com.java.train.member.entity.MemberLoginResp;
import com.java.train.member.req.MemberLoginReq;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.req.MemberSendCodeReq;
import com.java.train.member.service.MemberServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemController {

    @Resource
    private MemberServiceImpl memberService;

    @GetMapping("/count")
    public CommonResp<Integer> COUNT()
    {
        int count=memberService.counts();
        CommonResp<Integer> commonResp=new CommonResp<>();
        commonResp.setContent(count);
        return  commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register( @Valid @RequestBody  MemberRegisterReq req)
    {
        long register=memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/sendCode")
    public CommonResp<Long> SendCode(@Valid MemberSendCodeReq req)
    {

        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> Login(@RequestBody MemberLoginReq req)
    {

        MemberLoginResp memberLoginResp = memberService.Login(req);
        return new CommonResp<MemberLoginResp>(memberLoginResp);
    }







}
