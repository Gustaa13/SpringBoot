package com.gustaa13.tdd_event_city_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustaa13.tdd_event_city_api.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
