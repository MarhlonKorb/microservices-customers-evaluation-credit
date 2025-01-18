package com.avaliadorcredito.application.exception;

public class ErroComunicacaoMicroservicesException extends Exception {
    private final Integer status;
    public ErroComunicacaoMicroservicesException(String msg, Integer status){
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
