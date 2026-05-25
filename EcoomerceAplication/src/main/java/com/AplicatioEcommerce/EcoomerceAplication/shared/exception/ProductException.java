package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class ProductException extends ServiceException {
    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }
    public ProductException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
