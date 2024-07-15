package com.java.train.business.controller.Admin;

import com.java.train.business.req.TrainQueryReq;
import com.java.train.business.req.TrainSaveReq;
import com.java.train.business.resp.TrainQueryResp;
import com.java.train.business.service.Impl.TrainSeatServiceImpl;
import com.java.train.business.service.Impl.TrainServiceImpl;
import com.java.train.business.service.TrainSeatService;
import com.java.train.business.service.TrainService;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {

    @Resource
    private TrainServiceImpl trainService;

    @Resource
    private TrainSeatServiceImpl trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSaveReq req) {
        trainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req) {
        PageResp<TrainQueryResp> list = trainService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList() {
        List<TrainQueryResp> list = trainService.queryALL();
        return new CommonResp<>(list);
    }

    @GetMapping("/gen-seat/{trainCode}")
    public CommonResp<Object> genSeat(@PathVariable String trainCode) {
        trainSeatService.genTrainSeat(trainCode);
        return new CommonResp<>();
    }

}
