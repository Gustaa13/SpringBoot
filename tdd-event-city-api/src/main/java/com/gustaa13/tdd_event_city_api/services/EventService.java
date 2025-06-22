package com.gustaa13.tdd_event_city_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustaa13.tdd_event_city_api.dto.EventDTO;
import com.gustaa13.tdd_event_city_api.entities.City;
import com.gustaa13.tdd_event_city_api.entities.Event;
import com.gustaa13.tdd_event_city_api.repositories.EventRepository;
import com.gustaa13.tdd_event_city_api.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {

        try{
            Event event = repository.getReferenceById(id);
            copyEventDTOtoEvent(dto, event);
            event = repository.save(event);

            return new EventDTO(event);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        
    }

    private void copyEventDTOtoEvent(EventDTO dto, Event event) {
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(new City(dto.getCityId(), null));
    }
}
