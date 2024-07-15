package com.java.train.business.controller.Admin;

import com.java.train.business.req.TrainCarriageSaveReq;
import com.java.train.business.resp.TrainCarriageQueryResp;
import com.java.train.business.service.Impl.TrainCarriageServiceImpl;
import com.java.train.business.service.TrainCarriageService;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.TrainCarriageQueryReq;
import com.java.train.business.req.TrainCarriageSaveReq;
import com.java.train.business.resp.TrainCarriageQueryResp;
import com.java.train.business.service.TrainCarriageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train-carriage")
public class TrainCarriageAdminController {

    @Resource
    private TrainCarriageServiceImpl trainCarriageService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainCarriageSaveReq req) {
        trainCarriageService.save(req);
        return new CommonResp<>();
    }
    
    @GetMapping("/query-list")
    public CommonResp<PageResp <TrainCarriageQueryResp>> queryList(@Valid TrainCarriageQueryReq req) {
        PageResp<TrainCarriageQueryResp> list = trainCarriageService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainCarriageService.delete(id);
        return new CommonResp<>();
    }

}
