package com.AplicatioEcommerce.EcoomerceAplication.controller;

import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerDTOResponse;
import com.AplicatioEcommerce.EcoomerceAplication.DTO.CustomerRequestDTO;
import com.AplicatioEcommerce.EcoomerceAplication.models.repository.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController controller;
    //Inyectamos el mock

    @Mock
    private CustomerService customerService;
    //Inyectamos el servicio

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setup() {
    	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    	validator.afterPropertiesSet();
    	mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setValidator(validator)
                .build();

        objectMapper = new ObjectMapper();
    }
    
    @Test
    void getAllRetornaListaVacia() throws Exception{
    	when(customerService.getallCustomer()).thenReturn(Collections.emptyList());
    	mockMvc.perform(get("/api/customers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sucess").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.data").isEmpty());
    }
    
    @Test
    void getAllCustomersconLista()throws Exception {
    	List<CustomerDTOResponse> lista = List.of(
    			new CustomerDTOResponse("Juan", "juan@email.com", "ES1234567890123456789012"),
                new CustomerDTOResponse("Ana", "ana@email.com", "ES9876543210987654321098")
    			);
    	when(customerService.getallCustomer()).thenReturn(lista);
    	mockMvc.perform(get("/api/customers"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.sucess").value(true))
    	.andExpect(jsonPath("$.data.length()").value(2))
    	.andExpect(jsonPath("$.data[0].firstName").value("Juan"))
    	.andExpect(jsonPath("$.data[0].email").value("juan@email.com"))
    	.andExpect(jsonPath("$.data[1].firstName").value("Ana"));
    }
    
    @Test
    void registrar_sinCamposObligatorios_deberiaRetornar400() throws Exception{
    	mockMvc.perform(post("/api/customers")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content("{}"))
    		.andExpect(status().isBadRequest());
    }
    
    @Test
    void registrarsindatosvalidos() throws Exception{
    	CustomerRequestDTO dto = new CustomerRequestDTO(
                "Pedro", "Lopez", "7987654321213234", "pedro@email.com", "Segura99!sdsasdasdsadasd", "ES0000111122223333444455");
    	
        CustomerDTOResponse response = new CustomerDTOResponse(
                "Juan", "juan@email.com", "ES1234567890123456789012");
        when(customerService.anadirCustomer(any(CustomerRequestDTO.class))).thenReturn(response);
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        	.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$.sucess").value(false));
    }
    
    @Test
    void actualizar_conDatosValidos_deberiaRetornar200() throws Exception {
        CustomerRequestDTO dto = new CustomerRequestDTO(
                "Pedro", "Lopez", "7987654321", "pedro@email.com", "Segura99!", "ES0000111122223333444455");
        CustomerDTOResponse response = new CustomerDTOResponse(
                "Pedro", "pedro@email.com", "ES0000111122223333444455");

        when(customerService.updateCustomer(eq(1L), any(CustomerRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucess").value(true))
                .andExpect(jsonPath("$.data.firstName").value("Pedro")); 	
    }
    
    
    
    
    
    

}
