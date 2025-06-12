package com.gustaa13.crud_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustaa13.crud_api.dto.ClientDTO;
import com.gustaa13.crud_api.entities.Client;
import com.gustaa13.crud_api.repositories.ClientRepository;
import com.gustaa13.crud_api.service.exceptions.DatabaseIntegrityException;
import com.gustaa13.crud_api.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);

        return list.map(client -> new ClientDTO(client));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDTOtoEntity(dto, client);
        repository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDTOtoEntity(dto, entity);
            entity = repository.save(entity);

            return new ClientDTO(entity);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found"); 
        }
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) throw new ResourceNotFoundException("Entity not found");

        try {
            repository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Database Integrity Violation");
        }
    }

    private void copyDTOtoEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
