package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class InvoiceException extends ServiceException {
    public InvoiceException(ErrorCode errorCode) {
        super(errorCode);
    }
    public InvoiceException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
