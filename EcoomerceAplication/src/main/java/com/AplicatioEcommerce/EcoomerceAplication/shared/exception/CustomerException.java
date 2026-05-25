package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class CustomerException extends ServiceException {
    public CustomerException(ErrorCode errorCode) {
        super(errorCode);
    }
    public CustomerException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
