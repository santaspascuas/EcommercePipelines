package com.AplicatioEcommerce.EcoomerceAplication.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.Utiles.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomerException(CustomerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error("Customer error: " + ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(AdressException.class)
    public ResponseEntity<ApiResponse<Object>> handleAddressException(AdressException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(AdressNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleAddressNotFound(AdressNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiResponse<Object>> handleProductException(ProductException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ApiResponse<Object>> handleSellerException(SellerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleSellerNotFound(SellerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ApiResponse<Object>> handleOrderException(OrderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "; " + b);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error("Validacion fallida: " + message, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "; " + b);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error("Validacion fallida: " + message, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoResourceFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        log.error("Error no controlado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseUtil.error("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    
    @ExceptionHandler(OrderStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleOrderStatusException(OrderStatusException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity<ApiResponse<Object>> handleStockException(StockException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseUtil.error(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }
}
