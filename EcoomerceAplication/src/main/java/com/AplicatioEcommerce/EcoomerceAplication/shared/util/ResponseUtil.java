package com.AplicatioEcommerce.EcoomerceAplication.shared.util;

import java.time.LocalDateTime;

public class ResponseUtil {
	
	//Respuesta generica y ordenada
	
	public static <T> ApiResponse<T> error( String message,int errorCode){
		ApiResponse<T> response = new ApiResponse<>();
		response.setSucess(false);
		response.setData(null);
		response.setMessage(message);
		response.setErrorCode(errorCode);
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSucess(true);
		response.setData(data);
		response.setMessage(message);
		response.setErrorCode(null);
		response.setTimestamp(LocalDateTime.now());
		return response;
	}

}
