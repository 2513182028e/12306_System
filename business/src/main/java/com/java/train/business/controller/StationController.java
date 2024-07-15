package com.java.train.business.controller;
import com.java.train.business.service.Impl.StationServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.StationQueryReq;
import com.java.train.business.req.StationSaveReq;
import com.java.train.business.resp.StationQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationServiceImpl StationService;

    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryList() {
        List<StationQueryResp> list = StationService.queryAll();
        return new CommonResp<>(list);
    }

}
