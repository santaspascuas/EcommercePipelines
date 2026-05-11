package com.AplicatioEcommerce.EcoomerceAplication.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
