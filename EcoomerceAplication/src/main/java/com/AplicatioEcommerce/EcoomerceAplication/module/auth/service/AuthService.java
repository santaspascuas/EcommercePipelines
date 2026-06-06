package com.AplicatioEcommerce.EcoomerceAplication.module.auth.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.AplicatioEcommerce.EcoomerceAplication.module.auth.dto.AuthResult;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.dto.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.module.customer.service.CustomerService;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.dto.LoginRequest;
import com.AplicatioEcommerce.EcoomerceAplication.shared.util.JwtUtil;
import com.AplicatioEcommerce.EcoomerceAplication.shared.exception.AuthenticationException;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.shared.model.RefreshToken;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.security.CustomUserDetails;
import com.AplicatioEcommerce.EcoomerceAplication.module.auth.security.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final CookieService cookieService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;
    private final CustomerService customerService;
    
    private static final int MAX_FAILED_ATTEMPTS = 3;

    public AuthService(AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            CookieService cookieService,
            RefreshTokenService refreshTokenService,
            JwtUtil jwtUtil,
            CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.cookieService = cookieService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
        this.customerService = customerService;
    }

    public AuthResult loguinCustomer(LoginRequest loguin) {
        log.info("[loguinCustomer] Inicio");
        
        //Primero encontramos la cuenta y verificamos
        
        Customer customerEncontrado = customerService.encuentra(loguin.getEmail());
        //Econtrado ahora hay que validar.
        if(!customerEncontrado.isAccountNonExpired()) {
        	//Si es falso, la cuenta esta bloqueada	
        	throw new AuthenticationException("Usuario con cuenta expirada");
        }
        
        if(!customerEncontrado.isAccountNonLocked()) {
        	throw new AuthenticationException("Usuario bloqueado por intentos");
        }
        
        
        try {
        	//Esto es la autenticacion.
        	
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loguin.getEmail(), loguin.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loguin.getEmail());
            Customer customer = userDetails.getCustomer();

            if (customer.getFailedAttempts() > 0) {
                resetearIntentos(customer);
            }

            String accessToken = jwtUtil.generaToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(customer);

            ResponseCookie accessCookie = cookieService.createJwtCookie(accessToken);
            ResponseCookie refreshCookie = cookieService.createRefreshCookie(refreshToken.getToken());

            return new AuthResult(accessCookie, refreshCookie);
        } catch (BadCredentialsException e) {
        	contarIntentosAcceso(customerEncontrado);
            throw new AuthenticationException("Error en la identificacion del usuario");
        }
    }

    public CustomerDTOResponse register(CustomerRequestDTO request) {
        log.info("[register] Inicio");
        return customerService.registerCustomer(request);
    }

    public AuthResult refreshToken(HttpServletRequest request) {
        log.info("[refreshToken] Inicio");
        String refreshTokenValue = cookieService.extractRefreshFromCookies(request);
        if (refreshTokenValue == null) {
            throw new AuthenticationException("Refresh token no encontrado");
        }

        RefreshToken rotated = refreshTokenService.validateAndRotate(refreshTokenValue);
        Customer customer = rotated.getCustomer();

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(customer.getEmail());
        String newAccessToken = jwtUtil.generaToken(userDetails);

        ResponseCookie accessCookie = cookieService.createJwtCookie(newAccessToken);
        ResponseCookie refreshCookie = cookieService.createRefreshCookie(rotated.getToken());

        return new AuthResult(accessCookie, refreshCookie);
    }

    public AuthResult logout(HttpServletRequest request) {
        String refreshTokenValue = cookieService.extractRefreshFromCookies(request);
        if (refreshTokenValue != null) {
            refreshTokenService.revokeAndDeleteByToken(refreshTokenValue);
        }

        ResponseCookie clearAccess = cookieService.clearJwtCookie();
        ResponseCookie clearRefresh = cookieService.clearRefreshCookie();

        return new AuthResult(clearAccess, clearRefresh);
    }
    
    public void contarIntentosAcceso(Customer customer) {
    	log.info("[contarIntentosAcceso] Inicio");
    	log.debug("Se activa la funcion con el fallo de credenciales");
    	int intentossumados = customer.getFailedAttempts() + 1;
    	log.debug("[contarIntentosAcceso] intentos={}", intentossumados);
    	customer.setFailedAttempts(intentossumados);
    	
    	if(intentossumados >= MAX_FAILED_ATTEMPTS) {
    		customer.setAccountNonLocked(false);
    		customer.setLockTime(LocalDateTime.now());
    	}
    	
    	customerService.guardaLoguinCustomer(customer);
    	log.info("[contarIntentosAcceso] FIN---");
    }
    
    public void resetearIntentos(Customer customer) {
    	log.info("[resetearIntentos] Inicio");
    	customer.setFailedAttempts(0);
    	customer.setAccountNonLocked(true);
    	customer.setLockTime(null);
    	customerService.guardaLoguinCustomer(customer);
    }
    
    
    
    
    
    
    
}
