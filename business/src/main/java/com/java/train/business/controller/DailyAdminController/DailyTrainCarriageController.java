package com.java.train.business.controller.DailyAdminController;
import com.java.train.business.service.Impl.DailyTrainCarriageServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.DailyTrainCarriageQueryReq;
import com.java.train.business.req.DailyTrainCarriageSaveReq;
import com.java.train.business.resp.DailyTrainCarriageQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/DailyTrainCarriage")
public class DailyTrainCarriageController {

    @Resource
    private DailyTrainCarriageServiceImpl DailyTrainCarriageService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainCarriageSaveReq req) {
        DailyTrainCarriageService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainCarriageQueryResp>> queryList(@Valid DailyTrainCarriageQueryReq req) {
        PageResp<DailyTrainCarriageQueryResp> list = DailyTrainCarriageService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        DailyTrainCarriageService.delete(id);
        return new CommonResp<>();
    }

}
