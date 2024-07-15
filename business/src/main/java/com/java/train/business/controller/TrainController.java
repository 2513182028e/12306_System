package com.java.train.business.controller;


import cn.hutool.core.bean.BeanUtil;
import com.java.train.business.entity.Train;
import com.java.train.business.resp.TrainQueryResp;
import com.java.train.business.service.Impl.TrainServiceImpl;
import com.java.train.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {


    @Resource
    private TrainServiceImpl trainService;

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {

        List<Train> list = trainService.list();
        List<TrainQueryResp> trainQueryResps = BeanUtil.copyToList(list, TrainQueryResp.class);
        return new CommonResp<>(trainQueryResps);
    }

}
