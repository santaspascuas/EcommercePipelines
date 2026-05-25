package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class AdressException extends ServiceException {
    public AdressException() {
        super(GlobalErrorCodeConstants.ADDRESS_EXCEPTION);
    }
    public AdressException(String detail) {
        super(GlobalErrorCodeConstants.ADDRESS_EXCEPTION, detail);
    }
}
