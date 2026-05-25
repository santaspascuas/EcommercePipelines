package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public class AuthenticationException extends ServiceException {
    public AuthenticationException(String detail) {
        super(GlobalErrorCodeConstants.AUTH_LOGIN_FAILED, detail);
    }
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
    public AuthenticationException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
