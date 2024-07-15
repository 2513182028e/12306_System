package com.java.train.${module}.controller;
import com.java.train.${module}.service.${entity}ServiceImpl;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.CommonResp;
import com.java.train.common.resp.PageResp;
import com.java.train.${module}.req.${entity}QueryReq;
import com.java.train.${module}.req.${entity}SaveReq;
import com.java.train.${module}.resp.${entity}QueryResp;
import com.java.train.${module}.service.${entity}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${entity}")
public class ${entity}Controller {

    @Resource
    private ${entity}ServiceImpl ${entity}Service;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody ${entity}SaveReq req) {
        ${entity}Service.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<${entity}QueryResp>> queryList(@Valid ${entity}QueryReq req) {
        PageResp<${entity}QueryResp> list = ${entity}Service.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        ${entity}Service.delete(id);
        return new CommonResp<>();
    }

}
