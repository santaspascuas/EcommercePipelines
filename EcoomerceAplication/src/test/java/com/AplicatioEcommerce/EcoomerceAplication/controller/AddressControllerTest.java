package com.AplicatioEcommerce.EcoomerceAplication.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.AdressDTOUpdate;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.AddressMapper;
import com.AplicatioEcommerce.EcoomerceAplication.mappers.CustomerMapper;
import com.AplicatioEcommerce.EcoomerceAplication.models.Address;
import com.AplicatioEcommerce.EcoomerceAplication.models.Customer;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class AddressControllerTest {
	
	 @InjectMocks
	 private AddressController controllerAdress;
	 
	 @Mock
	 private AddressService addreservice;
	 
	 private MockMvc mockMvc;
	 private ObjectMapper objectMapper;
	 
	    @BeforeEach
	    void setup() {
	    	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    	validator.afterPropertiesSet();
	    	mockMvc = MockMvcBuilders.standaloneSetup(controllerAdress)
	                .setValidator(validator)
	                .build();

	        objectMapper = new ObjectMapper();
	    }
	    @Test
	    void getAllListaVacia() throws Exception {
	        when(addreservice.getAllAddresses()).thenReturn(Collections.emptyList());

	        mockMvc.perform(get("/api/addresses"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.sucess").value(true))
	                .andExpect(jsonPath("$.data").isArray())
	                .andExpect(jsonPath("$.data").isEmpty());
	    }
	    
	    @Test
	    void getAllListaAddress() throws Exception{
	    	//Finjo que recojo bien la lista
	        List<AdressDTOResponse> listaRapida = List.of(
	                new AdressDTOResponse(1L, "CalleFalsa", "LocalidadFalsa", "Madrid", "28760", "España"),
	                new AdressDTOResponse(2L, "CalleCalera", "LocalidadCalera", "Madrid", "28760", "España")
	            );
	        when(addreservice.getAllAddresses()).thenReturn(listaRapida);
	        mockMvc.perform(get("/api/addresses"))
	        	.andExpect(status().isOk())
	        	.andExpect(jsonPath("$.sucess").value(true))
	        	.andExpect(jsonPath("$.data.length()").value(2))
	        	.andExpect(jsonPath("$.data[0].calle").value("CalleFalsa"));	
	    }
	    //Validar que no vengan todos los campos.
	    
	    @Test
	    void obtenerDatosValidos() throws Exception{
	    	
	    	AdressDTOUpdate dto = new AdressDTOUpdate(
	    		"CalleFalsa","Localidad","Provincia","28760","PAIS");
	    	AdressDTOResponse response = new AdressDTOResponse(
	    			1L, "CalleFalsa", "LocalidadFalsa", "Madrid", "28760", "España");
	    	when(addreservice.updateAddress(eq(1L), any(AdressDTOUpdate.class))).thenReturn(response);
	    	mockMvc.perform(put("/api/addresses/1")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(objectMapper.writeValueAsString(dto)))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.sucess").value(true))
	    	.andExpect(jsonPath("$.data.calle").value("CalleFalsa"));
	    }
	    
	    @Test
	    void ObtenerdireccionesValido() throws Exception{
	    	
	    	//Datos de entrada.
	    	CustomerRequestDTO dto = new CustomerRequestDTO(
	                "Pedro", "Lopez", "7987654321213234", "pedro@email.com", "Segura99!sdsasdasdsadasd", "ES0000111122223333444455");
	    	//Devuelta.
	    	AdressDTOResponse response = new AdressDTOResponse(
	    			1L, "CalleFalsa", "LocalidadFalsa", "Madrid", "28760", "España");
	    	
	    	//Simulacion de usuario existente.
	    	Customer customerSimulado = new Customer();
	    	customerSimulado.setId(1L);
	    	customerSimulado.setFirstName("Pedro");
	    	customerSimulado.setLastName("Lopez");
	    	
	    	List<AdressDTOResponse> respuesta = List.of(response);
	    
	        when(addreservice.getAddressesByCustomer(1L)).thenReturn(respuesta);
	        mockMvc.perform(get("/api/addresses/customer/1"))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.sucess").value(true))
        	.andExpect(jsonPath("$.data.length()").value(1))
        	.andExpect(jsonPath("$.data[0].calle").value("CalleFalsa"));
	    }
	    
	    
	    
	    
	    //forzar una expcecion.
	    
	    
	    
	    
	    
	    



	    





}
