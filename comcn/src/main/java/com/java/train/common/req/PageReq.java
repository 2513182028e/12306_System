package com.java.train.common.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PageReq {
    @NotBlank(message = "【页码不能为空】")
    private  Integer page;

    @NotBlank(message = "【每页条数】不能为空")
    @Max(value = 100,message = "【每页条数】不能超过100")
    private Integer size;

}
