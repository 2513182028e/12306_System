package com.java.train.business.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.business.entity.Train;
import com.java.train.business.mapper.TrainMapper;
import com.java.train.business.req.TrainQueryReq;
import com.java.train.business.req.TrainSaveReq;
import com.java.train.business.resp.TrainQueryResp;
import com.java.train.common.exception.BusinessException;
import com.java.train.common.exception.BusinessExceptionEnum;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.ShowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements IService<Train> {


    private static final Logger LOG = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Resource
    private TrainMapper trainMapper;

    public void save(TrainSaveReq req)
    {
        DateTime now=DateTime.now();
        Train train= BeanUtil.copyProperties(req,Train.class);
        if(ObjectUtil.isNull(train.getId()))
        {
                Train trainDB=selectByUnique(train.getCode());
            if (ObjectUtil.isNotEmpty(trainDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
            }

            train.setId(ShowUtil.getSnowflakeNextId());
            train.setCreateTime(now);
            train.setUpdateTime(now);
            trainMapper.insert(train);
        }else {
                train.setUpdateTime(now);
                trainMapper.updateById(train);
        }
    }

    private Train selectByUnique(String code)
    {
        QueryWrapper<Train> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",code);
        List<Train> list=trainMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(list))
        {
            return list.get(0);
        }
        return null;
    }
    public PageResp<TrainQueryResp> queryList(TrainQueryReq req) {


        QueryWrapper<Train> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("code");
        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Train> trainList = trainMapper.selectList(queryWrapper);

        PageInfo<Train> pageInfo = new PageInfo<>(trainList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<TrainQueryResp> list = BeanUtil.copyToList(trainList, TrainQueryResp.class);

        PageResp<TrainQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setLists(list);
        return pageResp;
    }



    public void delete(Long id) {
        trainMapper.deleteById(id);
    }

    public List<TrainQueryResp> queryALL()
    {
        return  BeanUtil.copyToList(selectAll(),TrainQueryResp.class);
    }

    public List<Train> selectAll()
    {
        QueryWrapper<Train> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("code");
        return trainMapper.selectList(queryWrapper);
    }
}
