package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class InvoiceNotFoundException extends ServiceException {
    public InvoiceNotFoundException() {
        super(GlobalErrorCodeConstants.INVOICE_NOT_FOUND);
    }
    public InvoiceNotFoundException(String detail) {
        super(GlobalErrorCodeConstants.INVOICE_NOT_FOUND, detail);
    }
}
