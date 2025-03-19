package com.weddingplanner.TestCaseForClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weddingplanner.controller.ClientController;
import com.weddingplanner.model.Client;
import com.weddingplanner.service.ClientService;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    public void testCreateClient() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  

        Client newClient = new Client();
        newClient.setId(1L);
        newClient.setName("John Doe");
        newClient.setWeddingDate(LocalDate.of(2025, 6, 15));
        newClient.setBudget(new BigDecimal("15000"));

        when(clientService.registerClient(any(Client.class))).thenReturn(newClient);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newClient)))  
                .andExpect(status().isCreated())  
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.weddingDate").value("2025-06-15"))
                .andExpect(jsonPath("$.budget").value(15000));
    }



    @Test
    public void testGetClientById() throws Exception {
        Client existingClient = new Client();
        existingClient.setId(1L);
        existingClient.setName("Jane Doe");
        existingClient.setWeddingDate(LocalDate.of(2025, 6, 15));
        existingClient.setBudget(new BigDecimal("20000"));

        when(clientService.getClientById(anyLong())).thenReturn(existingClient);

        mockMvc.perform(get("/clients/{id}", 1L))
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.weddingDate").value("2025-06-15"))
                .andExpect(jsonPath("$.budget").value(20000));
    }
}
