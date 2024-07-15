package com.java.train.business.service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.business.service.DailyTrainTicketService;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.ShowUtil;
import com.java.train.business.entity.DailyTrainTicket;
import com.java.train.business.mapper.DailyTrainTicketMapper;
import com.java.train.business.req.DailyTrainTicketQueryReq;
import com.java.train.business.req.DailyTrainTicketSaveReq;
import com.java.train.business.resp.DailyTrainTicketQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DailyTrainTicketServiceImpl extends ServiceImpl<DailyTrainTicketMapper,DailyTrainTicket> implements DailyTrainTicketService {



    private static final Logger LOG = LoggerFactory.getLogger(DailyTrainTicketServiceImpl.class);


    @Resource
    private DailyTrainTicketMapper DailyTrainTicketmapper;




    public void save(DailyTrainTicketSaveReq req) {
        DateTime now = DateTime.now();
        DailyTrainTicket DailyTrainTicketm = BeanUtil.copyProperties(req, DailyTrainTicket.class);
        if (ObjectUtil.isNull(DailyTrainTicketm.getId())) {
            DailyTrainTicketm.setId(ShowUtil.getSnowflakeNextId());
            DailyTrainTicketm.setCreateTime(now);
            DailyTrainTicketm.setUpdateTime(now);
            DailyTrainTicketmapper.insert(DailyTrainTicketm);
        } else {
            DailyTrainTicketm.setUpdateTime(now);
            DailyTrainTicketmapper.updateById(DailyTrainTicketm);
        }
    }

    public PageResp<DailyTrainTicketQueryResp> queryList(DailyTrainTicketQueryReq req) {
    LambdaQueryWrapper<DailyTrainTicket> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(DailyTrainTicket::getId);

            LOG.info("查询页码：{}", req.getPage());
            LOG.info("每页条数：{}", req.getSize());
            PageHelper.startPage(req.getPage(), req.getSize());
            List<DailyTrainTicket> selectedList = DailyTrainTicketmapper.selectList(lambdaQueryWrapper);
            PageInfo<DailyTrainTicket> pageInfo = new PageInfo<>(selectedList);
                LOG.info("总行数：{}", pageInfo.getTotal());
                LOG.info("总页数：{}", pageInfo.getPages());

                List<DailyTrainTicketQueryResp> list = BeanUtil.copyToList(selectedList, DailyTrainTicketQueryResp.class);

                    PageResp<DailyTrainTicketQueryResp> pageResp = new PageResp<>();
                        pageResp.setTotal(pageInfo.getTotal());
                        pageResp.setLists(list);
                        return pageResp;

    }



    public void delete(Long id) {
        DailyTrainTicketmapper.deleteById(id);
    }
}
