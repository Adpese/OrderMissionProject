package com.sopra.repository;

import org.springframework.data.repository.CrudRepository;

import com.sopra.entity.Itinerary;


public interface ItineraryRepository extends CrudRepository<Itinerary, Integer> {

}
