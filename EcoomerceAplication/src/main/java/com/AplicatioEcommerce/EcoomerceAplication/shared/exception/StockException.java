package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class StockException extends ServiceException {
    public StockException(ErrorCode errorCode) {
        super(errorCode);
    }
    public StockException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
