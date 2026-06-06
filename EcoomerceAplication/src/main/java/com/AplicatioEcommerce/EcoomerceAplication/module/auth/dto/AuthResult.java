package com.AplicatioEcommerce.EcoomerceAplication.module.auth.dto;

import org.springframework.http.ResponseCookie;

public record AuthResult(ResponseCookie accessCookie,
		ResponseCookie refreshCookie) {
}
