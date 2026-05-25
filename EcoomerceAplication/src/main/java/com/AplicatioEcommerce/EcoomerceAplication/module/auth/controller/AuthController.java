package com.AplicatioEcommerce.EcoomerceAplication.module.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AplicatioEcommerce.EcoomerceAplication.module.auth.dto.AuthResult;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.dto.LoginRequest;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = authService.loguinCustomer(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, result.accessCookie().toString())
                .header(HttpHeaders.SET_COOKIE, result.refreshCookie().toString())
                .body(ResponseUtil.success("Login correcto", null));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<Void>> refresh(HttpServletRequest request) {
        AuthResult result = authService.refreshToken(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, result.accessCookie().toString())
                .header(HttpHeaders.SET_COOKIE, result.refreshCookie().toString())
                .body(ResponseUtil.success("Token renovado correctamente", null));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
        AuthResult result = authService.logout(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, result.accessCookie().toString())
                .header(HttpHeaders.SET_COOKIE, result.refreshCookie().toString())
                .body(ResponseUtil.success("Sesión cerrada correctamente", null));
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CustomerDTOResponse>> register(@Valid @RequestBody CustomerRequestDTO request) {
        CustomerDTOResponse created = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseUtil.success("Registro correcto", created));
    }



}
