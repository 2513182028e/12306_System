package com.java.train.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.train.business.entity.TrainStation;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace
public interface TrainStationMapper extends BaseMapper<TrainStation> {
}
