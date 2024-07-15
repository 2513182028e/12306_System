package com.java.train.common.resp;

import lombok.Data;

@Data
public class CommonResp<T> {


    private boolean success=true;

    private  String message;

    private T content;
    public CommonResp()
    {

    }
    public CommonResp(T content)
    {
        this.content=content;
    }
}
