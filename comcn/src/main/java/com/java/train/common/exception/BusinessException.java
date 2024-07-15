package com.java.train.common.exception;



public class BusinessException  extends  RuntimeException{

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return  this;
    }

    public BusinessException(String message, BusinessExceptionEnum e) {
        super(message);
        this.e = e;
    }

    public BusinessException(String message, Throwable cause, BusinessExceptionEnum e) {
        super(message, cause);
        this.e = e;
    }

    public BusinessException(Throwable cause, BusinessExceptionEnum e) {
        super(cause);
        this.e = e;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BusinessExceptionEnum e) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.e = e;
    }

    public BusinessExceptionEnum getAnEnum() {
        return e;
    }

    public void setAnEnum(BusinessExceptionEnum e) {
        this.e = e;
    }


}
