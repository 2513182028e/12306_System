package com.java.train.business.controller.DailyAdminController;
import com.java.train.business.service.Impl.DailyTrainTicketServiceImpl;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.business.req.DailyTrainTicketQueryReq;
import com.java.train.business.req.DailyTrainTicketSaveReq;
import com.java.train.business.resp.DailyTrainTicketQueryResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/DailyTrainTicket")
public class DailyTrainTicketController {

    @Resource
    private DailyTrainTicketServiceImpl DailyTrainTicketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainTicketSaveReq req) {
        DailyTrainTicketService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = DailyTrainTicketService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        DailyTrainTicketService.delete(id);
        return new CommonResp<>();
    }

}
