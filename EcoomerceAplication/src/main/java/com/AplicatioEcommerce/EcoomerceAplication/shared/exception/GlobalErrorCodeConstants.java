package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

public enum GlobalErrorCodeConstants implements ErrorCode {

    // — Éxito
    SUCCESS(0, "OK"),

    // — Errores de cliente (4xx)
    BAD_REQUEST(400, "Parámetros de la solicitud incorrectos"),
    UNAUTHORIZED(401, "No autenticado"),
    FORBIDDEN(403, "Sin permiso para esta operación"),
    NOT_FOUND(404, "Recurso no encontrado"),
    LOCKED(423, "Cuenta bloqueada, inténtalo más tarde"),
    TOO_MANY_REQUESTS(429, "Demasiadas solicitudes, inténtalo más tarde"),

    // — Errores de servidor (5xx)
    INTERNAL_SERVER_ERROR(500, "Error interno del servidor"),
    NOT_IMPLEMENTED(501, "Funcionalidad no implementada"),

    // — Códigos de negocio propios (1xxx)
    REPEATED_REQUEST(900, "Solicitud duplicada, inténtalo más tarde"),

    // Auth (1-001-xxx)
    AUTH_LOGIN_FAILED(1001001, "Credenciales incorrectas"),
    AUTH_ACCOUNT_LOCKED(1001002, "Cuenta bloqueada por exceso de intentos"),
    AUTH_ACCOUNT_EXPIRED(1001003, "Cuenta expirada"),
    AUTH_TOKEN_INVALID(1001004, "Token inválido o expirado"),
    AUTH_REFRESH_TOKEN_NOT_FOUND(1001005, "Refresh token no encontrado"),

    // Customer (1-002-xxx)
    CUSTOMER_NOT_FOUND(1002001, "Cliente no encontrado"),
    CUSTOMER_EMAIL_DUPLICATE(1002002, "El email ya está registrado"),

    // Address (1-003-xxx)
    ADDRESS_NOT_FOUND(1003001, "Dirección no encontrada"),
    ADDRESS_EXCEPTION(1003002, "Error en la recuperacion de direccion"),
    

    // Invoice (1-004-xxx)
    INVOICE_NOT_FOUND(1004001, "Factura no encontrada"),
    INVOICE_ALREADY_CANCELLED(1004002, "La factura ya está cancelada"),
    INVOICE_CANNOT_EDIT_ISSUED(1004003, "No se puede modificar una factura ya emitida"),
    INVOICE_NUMBER_DUPLICATE(1004004, "Número de factura duplicado"),
    INVOICE_INVALID_STATUS_TRANSITION(1004005, "Transición de estado no permitida"),

    // Product (1-005-xxx)
    PRODUCT_NOT_FOUND(1005001, "Producto no encontrado"),
    PRODUCT_NAME_DUPLICATE(1005002, "Ya existe un producto con ese nombre"),
    PRODUCT_CODE_DUPLICATE(1005003, "Ya existe un producto con ese código"),

    // Client (1-006-xxx)
    CLIENT_NOT_FOUND(1006001, "Cliente de facturación no encontrado"),
    CLIENT_EMAIL_DUPLICATE(1006002, "Ya existe un cliente con ese email"),
    CLIENT_NIF_DUPLICATE(1006003, "Ya existe un cliente con ese NIF"),

    // Stock (1-007-xxx)
    STOCK_INSUFFICIENT(1007001, "Stock insuficiente"),
    STOCK_NOT_FOUND(1007002, "Stock no encontrado para el producto");

    private final int code;
    private final String message;

    GlobalErrorCodeConstants(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() { return code; }

    @Override
    public String getMessage() { return message; }
}
