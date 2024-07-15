package com.java.train.business.service.Impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.business.entity.Station;
import com.java.train.business.mapper.StationMapper;
import com.java.train.business.req.StationQueryReq;
import com.java.train.business.req.StationSaveReq;
import com.java.train.business.resp.StationQueryResp;
import com.java.train.common.exception.BusinessException;
import com.java.train.common.exception.BusinessExceptionEnum;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.ShowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IService<Station> {

    private static final Logger LOG = LoggerFactory.getLogger(StationServiceImpl.class);


    @Autowired
    private StationMapper stationMapper;

    public void save(StationSaveReq req) {
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);
        if (ObjectUtil.isNull(station.getId())) {

            // 保存之前，先校验唯一键是否存在
            Station stationDB = selectByUnique(req.getName());
            if (ObjectUtil.isNotEmpty(stationDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            }

            station.setId(ShowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationMapper.insert(station);
        } else {
            station.setUpdateTime(now);
            stationMapper.updateById(station);
        }
    }

    private Station selectByUnique(String name) {
        QueryWrapper<Station> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        List<Station> stationList = stationMapper.selectList(queryWrapper);
        if(stationList!=null)
        {
            return stationList.get(0);
        }
        return null;
    }

    public PageResp<StationQueryResp> queryList(StationQueryReq req) {
//        StationExample stationExample = new StationExample();
//        stationExample.setOrderByClause("id desc");
//        StationExample.Criteria criteria = stationExample.createCriteria();

        LambdaQueryWrapper<Station> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Station::getId);

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Station> selectedList = stationMapper.selectList(lambdaQueryWrapper);
        PageInfo<Station> pageInfo = new PageInfo<>(selectedList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<StationQueryResp> list = BeanUtil.copyToList(selectedList, StationQueryResp.class);

        PageResp<StationQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setLists(list);
        return pageResp;
    }

    public void delete(Long id) {
        stationMapper.deleteById(id);
    }

    public List<StationQueryResp> queryAll() {
//        LambdaQueryWrapper<Station> lambdaQueryWrapper=new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.orderByAsc(Station::getNamePinyin);

        QueryWrapper<Station> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderBy(false,true,"name_pinyin");
        List<Station> stationList = stationMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(stationList, StationQueryResp.class);
    }

}
