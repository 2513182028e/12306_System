package com.java.train.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.java.train.member.entity.Member;
import com.java.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends IService<Member> {



}
