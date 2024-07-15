package com.java.train.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.train.member.entity.Passenger;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerMapper extends  BaseMapper<Passenger> {
}
