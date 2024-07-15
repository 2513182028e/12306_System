package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.train.common.exception.BusinessException;
import com.java.train.common.exception.BusinessExceptionEnum;
import com.java.train.common.util.JwtUtil;
import com.java.train.common.util.ShowUtil;
import com.java.train.member.entity.Member;
import com.java.train.member.entity.MemberLoginResp;
import com.java.train.member.mapper.MemberMapper;
import com.java.train.member.req.MemberLoginReq;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.req.MemberSendCodeReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberMapper memberMapper;


    private static final Logger LOG= LoggerFactory.getLogger(MemberServiceImpl.class);

    public int counts()
    {
        return memberMapper.count();
    }

    public long register(MemberRegisterReq req)
    {

        String mobile=req.getMobile();
//        MemberExample memberExample=new MemberExample();
//        memberExample.createCriteria().andMobileEqualTo(mobile);
        Member memberDB = getMemberByMobile(mobile);
        if(memberDB!=null)
        {
            throw  new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        //BeanUtils.copyProperties(member);
        //member.setId(System.currentTimeMillis());
        // 这里的System.currentTimeMillis()是得到该时刻的毫秒数，但是在高并发的场景下每个毫秒下会产生多个数据
        // 因此不准确，如果使用自增ID的话，不适用用分布式，分库分表的场景
        member.setId(ShowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);

        return member.getId();
    }

    public  void sendCode(MemberSendCodeReq req)
    {
       String mobile=req.getMobile();
        Member memberDB = getMemberByMobile(mobile);
        //如果手机号没有注册过
        if(memberDB==null)
        {
                Member member=new Member();
                member.setId(ShowUtil.getSnowflakeNextId());
                member.setMobile(mobile);
                memberMapper.insert(member);
        }
        //生成验证码
        String code= RandomUtil.randomNumbers(4);

        //保存短信记录表：手机号，短信验证码，有效期，是否已经使用过，业务类型，发生时间，使用时间
        LOG.info("手机号存在不插入记录");
        //对接短信通道（发送短信）(真实项目应该完成)
        LOG.info("生成短信验证码:{}",code);
    }

    public  MemberLoginResp Login(MemberLoginReq req)
    {
        String mobile=req.getMobile();
        String code=req.getCode();
        Member memberDB = getMemberByMobile(mobile);
        // 如果手机号没有注册过
        if(ObjectUtil.isNull(memberDB))
        {
           throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

//        //校验短信验证码
//        if(!"8888".equals(code))
//        {
//            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
//        }

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        HashMap<String,String> LoginMap=new HashMap<>();
        LoginMap.put("id", String.valueOf(memberDB.getId()));
        LoginMap.put("mobile",memberDB.getMobile());
        String token = JwtUtil.createToken(LoginMap);
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    private Member getMemberByMobile(String mobile) {
        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return memberMapper.selectOne(queryWrapper);
    }

}
