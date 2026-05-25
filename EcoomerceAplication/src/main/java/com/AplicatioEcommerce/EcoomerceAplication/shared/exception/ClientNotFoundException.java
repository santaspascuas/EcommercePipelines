package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class ClientNotFoundException extends ServiceException {
    public ClientNotFoundException() {
        super(GlobalErrorCodeConstants.CLIENT_NOT_FOUND);
    }
    public ClientNotFoundException(String detail) {
        super(GlobalErrorCodeConstants.CLIENT_NOT_FOUND, detail);
    }
}
