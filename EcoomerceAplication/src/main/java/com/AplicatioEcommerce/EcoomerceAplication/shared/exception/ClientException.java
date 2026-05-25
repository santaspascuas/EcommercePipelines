package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class ClientException extends ServiceException {
    public ClientException(ErrorCode errorCode) {
        super(errorCode);
    }
    public ClientException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
