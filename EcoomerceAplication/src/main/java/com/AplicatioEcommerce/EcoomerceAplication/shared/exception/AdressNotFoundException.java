package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class AdressNotFoundException extends ServiceException {
    public AdressNotFoundException() {
        super(GlobalErrorCodeConstants.ADDRESS_NOT_FOUND);
    }
    public AdressNotFoundException(String detail) {
        super(GlobalErrorCodeConstants.ADDRESS_NOT_FOUND, detail);
    }
}
