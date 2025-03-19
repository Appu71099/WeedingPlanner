package com.weddingplanner.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weddingplanner.exception.ClientNotFoundException;
import com.weddingplanner.model.Client;
import com.weddingplanner.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public List<Client> getAllClients(LocalDate weddingDate, BigDecimal minBudget, BigDecimal maxBudget) {
        return clientRepository.findAll(); 
    }
}