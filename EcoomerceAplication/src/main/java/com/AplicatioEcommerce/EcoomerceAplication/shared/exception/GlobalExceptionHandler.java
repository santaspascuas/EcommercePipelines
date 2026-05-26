package com.AplicatioEcommerce.EcoomerceAplication.shared.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ApiResponse;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse<Object>> handleServiceException(ServiceException ex) {
        int code = ex.getCode();
        HttpStatus status = resolveHttpStatus(code);
        return ResponseEntity.status(status)
                .body(ResponseUtil.error(ex.getMessage(), code));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseUtil.error(GlobalErrorCodeConstants.FORBIDDEN.getMessage(),
                        GlobalErrorCodeConstants.FORBIDDEN.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "; " + b);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(message, GlobalErrorCodeConstants.BAD_REQUEST.getCode()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "; " + b);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error(message, GlobalErrorCodeConstants.BAD_REQUEST.getCode()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoResourceFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseUtil.error(ex.getMessage(), GlobalErrorCodeConstants.NOT_FOUND.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        log.error("Error no controlado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseUtil.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getMessage(),
                        GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode()));
    }
    
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiResponse<Object>> handleProductExcetion(ProductException ex){
    	ApiResponse<Object> body = ResponseUtil.error(ex.getMessage(),
    			HttpStatus.BAD_REQUEST.value());
    	
    	return ResponseEntity
    			.status(HttpStatus.BAD_REQUEST)
    			.body(body);
    }
    
    
    
    
    
    

    private HttpStatus resolveHttpStatus(int code) {
        if (code == 401 || (code >= 1001001 && code <= 1001005)) return HttpStatus.UNAUTHORIZED;
        if (code == 403) return HttpStatus.FORBIDDEN;
        if (code == 404 || code == 1002001 || code == 1003001 || code == 1004001
                || code == 1005001 || code == 1006001 || code == 1007002) return HttpStatus.NOT_FOUND;
        if (code == 423) return HttpStatus.LOCKED;
        if (code == 429) return HttpStatus.TOO_MANY_REQUESTS;
        if (code == 409 || code == 1007001) return HttpStatus.CONFLICT;
        if (code >= 500) return HttpStatus.INTERNAL_SERVER_ERROR;
        return HttpStatus.BAD_REQUEST;
    }
}
