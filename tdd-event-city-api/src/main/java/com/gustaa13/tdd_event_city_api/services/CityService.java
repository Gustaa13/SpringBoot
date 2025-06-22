package com.gustaa13.tdd_event_city_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustaa13.tdd_event_city_api.dto.CityDTO;
import com.gustaa13.tdd_event_city_api.entities.City;
import com.gustaa13.tdd_event_city_api.repositories.CityRepository;
import com.gustaa13.tdd_event_city_api.services.exceptions.DatabaseException;
import com.gustaa13.tdd_event_city_api.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {

        List<City> cityList = repository.findAll(Sort.by("name"));

        return cityList.stream().map(city -> new CityDTO(city)).toList();
    }

    @Transactional
    public CityDTO insert(CityDTO cityDto) {

        City entity = new City();
        entity.setName(cityDto.getName());

        repository.save(entity);

        return new CityDTO(entity);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) throw new ResourceNotFoundException("Id not found " + id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
