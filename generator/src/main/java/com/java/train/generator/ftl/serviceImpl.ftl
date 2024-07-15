package com.java.train.${module}.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.common.resp.PageResp;
import com.java.train.common.util.ShowUtil;
import com.java.train.${module}.entity.${entity};
import com.java.train.${module}.mapper.${entity}Mapper;
import com.java.train.${module}.req.${entity}QueryReq;
import com.java.train.${module}.req.${entity}SaveReq;
import com.java.train.${module}.resp.${entity}QueryResp;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ${entity}ServiceImpl extends ServiceImpl<${entity}Mapper,${entity}> implements ${entity}Service {



    private static final Logger LOG = LoggerFactory.getLogger(${entity}ServiceImpl.class);


    @Resource
    private ${entity}Mapper ${entity}mapper;




    public void save(${entity}SaveReq req) {
        DateTime now = DateTime.now();
        ${entity} ${entity}m = BeanUtil.copyProperties(req, ${entity}.class);
        if (ObjectUtil.isNull(${entity}m.getId())) {
            ${entity}m.setId(ShowUtil.getSnowflakeNextId());
            ${entity}m.setCreateTime(now);
            ${entity}m.setUpdateTime(now);
            ${entity}mapper.insert(${entity}m);
        } else {
            ${entity}m.setUpdateTime(now);
            ${entity}mapper.updateById(${entity}m);
        }
    }

    public PageResp<${entity}QueryResp> queryList(${entity}QueryReq req) {
<#--        ${entity}Example ${entity}Example = new ${entity}Example();-->
<#--        ${entity}Example.setOrderByClause("id desc");-->
<#--        ${entity}Example.Criteria criteria = ${entity}Example.createCriteria();-->

<#--        LOG.info("查询页码：{}", req.getPage());-->
<#--        LOG.info("每页条数：{}", req.getSize());-->
<#--        PageHelper.startPage(req.getPage(), req.getSize());-->
<#--        List<${entity}> ${entity}List = ${entity}Mapper.selectByExample(${entity}Example);-->

<#--        PageInfo<${entity}> pageInfo = new PageInfo<>(${entity}List);-->
<#--        LOG.info("总行数：{}", pageInfo.getTotal());-->
<#--        LOG.info("总页数：{}", pageInfo.getPages());-->

<#--        List<${entity}QueryResp> list = BeanUtil.copyToList(${entity}List, ${entity}QueryResp.class);-->

<#--        PageResp<${entity}QueryResp> pageResp = new PageResp<>();-->
<#--        pageResp.setTotal(pageInfo.getTotal());-->
<#--        pageResp.setList(list);-->
<#--        return pageResp;-->
    LambdaQueryWrapper<${entity}> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(${entity}::getId);

            LOG.info("查询页码：{}", req.getPage());
            LOG.info("每页条数：{}", req.getSize());
            PageHelper.startPage(req.getPage(), req.getSize());
            List<${entity}> selectedList = ${entity}mapper.selectList(lambdaQueryWrapper);
            PageInfo<${entity}> pageInfo = new PageInfo<>(selectedList);
                LOG.info("总行数：{}", pageInfo.getTotal());
                LOG.info("总页数：{}", pageInfo.getPages());

                List<${entity}QueryResp> list = BeanUtil.copyToList(selectedList, ${entity}QueryResp.class);

                    PageResp<${entity}QueryResp> pageResp = new PageResp<>();
                        pageResp.setTotal(pageInfo.getTotal());
                        pageResp.setLists(list);
                        return pageResp;

    }



    public void delete(Long id) {
        ${entity}mapper.deleteById(id);
    }
}
