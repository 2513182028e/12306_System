package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.ShowUtil;
import com.java.train.member.resp.PassengerQueryResp;
import com.java.train.member.entity.Passenger;
import com.java.train.member.mapper.PassengerMapper;
import com.java.train.member.req.PassengerQueryReq;
import com.java.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {

    @Resource
    private  PassengerMapper passengerMapper;


    private static final Logger LOG = LoggerFactory.getLogger(PassengerServiceImpl.class);
    public void save(PassengerSaveReq req)
    {
        DateTime now=DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(ShowUtil.getSnowflakeNextId());
        passenger.setCreate_time(now);
        passenger.setUpdate_time(now);
        passengerMapper.insert(passenger);

    }


   public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req)
   {

       QueryWrapper<Passenger> queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("id",req.getMemberId());
       LOG.info("查询页码:{}",req.getPage());
       LOG.info("每页条数:{}",req.getSize());
       PageHelper.startPage(req.getPage(),req.getSize());
       List<Passenger> passengerList = passengerMapper.selectList(queryWrapper);

       PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
       PageResp<PassengerQueryResp> pageResp=new PageResp<>();
       List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
       pageResp.setLists(passengerQueryResps);
       pageResp.setTotal(pageInfo.getTotal());
       return  pageResp;
   }
   public void  deleteById(Long id)
   {
       passengerMapper.deleteById(id);
   }
   public List<PassengerQueryResp> queryMine()
   {
       Long Memberid = LoginMemberContext.getId();
       QueryWrapper<Passenger> queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("id",Memberid);
       List<Passenger> passengerList = passengerMapper.selectList(queryWrapper);

    return BeanUtil.copyToList(passengerList,PassengerQueryResp.class);
   }



}
