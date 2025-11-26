package com.flightapp.flightbookingwebflux.repository;

import com.flightapp.flightbookingwebflux.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    // Custom finder method
    Flux<Flight> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCase(String from, String to);

}
