package com.java.train.business.controller.DailyAdminController;
import com.java.train.business.service.Impl.DailyTrainStationServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.DailyTrainStationQueryReq;
import com.java.train.business.req.DailyTrainStationSaveReq;
import com.java.train.business.resp.DailyTrainStationQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/DailyTrainStation")
public class DailyTrainStationController {

    @Resource
    private DailyTrainStationServiceImpl DailyTrainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainStationSaveReq req) {
        DailyTrainStationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainStationQueryResp>> queryList(@Valid DailyTrainStationQueryReq req) {
        PageResp<DailyTrainStationQueryResp> list = DailyTrainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        DailyTrainStationService.delete(id);
        return new CommonResp<>();
    }

}
