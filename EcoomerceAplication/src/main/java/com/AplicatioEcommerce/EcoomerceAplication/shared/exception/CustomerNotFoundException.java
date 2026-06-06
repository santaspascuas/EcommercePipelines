package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class CustomerNotFoundException extends ServiceException {
    public CustomerNotFoundException() {
        super(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND);
    }
    public CustomerNotFoundException(String detail) {
        super(GlobalErrorCodeConstants.CUSTOMER_NOT_FOUND, detail);
    }
}
