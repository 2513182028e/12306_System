package com.java.train.business.controller.DailyAdminController;
import com.java.train.business.service.Impl.DailyTrainSeatServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.DailyTrainSeatQueryReq;
import com.java.train.business.req.DailyTrainSeatSaveReq;
import com.java.train.business.resp.DailyTrainSeatQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/DailyTrainSeat")
public class DailyTrainSeatController {

    @Resource
    private DailyTrainSeatServiceImpl DailyTrainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainSeatSaveReq req) {
        DailyTrainSeatService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainSeatQueryResp>> queryList(@Valid DailyTrainSeatQueryReq req) {
        PageResp<DailyTrainSeatQueryResp> list = DailyTrainSeatService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        DailyTrainSeatService.delete(id);
        return new CommonResp<>();
    }

}
