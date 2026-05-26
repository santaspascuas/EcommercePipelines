package com.AplicatioEcommerce.EcoomerceAplication.module.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CookieService {

    @Value("${app.cookie.secure:true}")
    private boolean secureCookie;

    @Value("${app.cookie.name:jwt}")
    private String cookieName;

    @Value("${app.cookie.max-age:7200}")
    private int accessMaxAge;

    private static final String REFRESH_COOKIE_NAME = "refresh_token";
    private static final int REFRESH_MAX_AGE = 7 * 24 * 60 * 60; // 7 días en segundos


    public ResponseCookie createJwtCookie(String token) {
        return ResponseCookie.from(cookieName, token)
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/")
                .maxAge(accessMaxAge)
                .build();
    }

    public ResponseCookie clearJwtCookie() {
        return ResponseCookie.from(cookieName, "")
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
    }

    public String extractJwtFromCookies(HttpServletRequest request) {
        return extractCookieValue(request, cookieName);
    }
    

    public ResponseCookie createRefreshCookie(String token) {
        return ResponseCookie.from(REFRESH_COOKIE_NAME, token)
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/api/auth/refresh")
                .maxAge(REFRESH_MAX_AGE)
                .build();
    }

    public ResponseCookie clearRefreshCookie() {
        return ResponseCookie.from(REFRESH_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(secureCookie)
                .sameSite("Strict")
                .path("/api/auth/refresh")
                .maxAge(0)
                .build();
    }

    public String extractRefreshFromCookies(HttpServletRequest request) {
        return extractCookieValue(request, REFRESH_COOKIE_NAME);
    }

    private String extractCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
