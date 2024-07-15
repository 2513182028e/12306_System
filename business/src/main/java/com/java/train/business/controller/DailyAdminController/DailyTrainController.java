package com.java.train.business.controller.DailyAdminController;
import com.java.train.business.service.Impl.DailyTrainServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.DailyTrainQueryReq;
import com.java.train.business.req.DailyTrainSaveReq;
import com.java.train.business.resp.DailyTrainQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/admin/DailyTrain")
public class DailyTrainController {

    @Resource
    private DailyTrainServiceImpl DailyTrainService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainSaveReq req) {
        DailyTrainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainQueryResp>> queryList(@Valid DailyTrainQueryReq req) {
        PageResp<DailyTrainQueryResp> list = DailyTrainService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        DailyTrainService.delete(id);
        return new CommonResp<>();
    }


    @GetMapping("/gen-daily/{date}")
    public CommonResp<Object> delete(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")Date date)
    {
        DailyTrainService.genDaily(date);
        return  new CommonResp<>();
    }

}
