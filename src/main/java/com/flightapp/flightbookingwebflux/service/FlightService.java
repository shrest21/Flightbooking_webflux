package com.flightapp.flightbookingwebflux.service;

import com.flightapp.flightbookingwebflux.model.Flight;
import com.flightapp.flightbookingwebflux.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repo;

    public Mono<Flight> addFlight(Flight flight) {
        return repo.save(flight);
    }

    public Flux<Flight> search(String from, String to) {
        return repo.findByFromPlaceIgnoreCaseAndToPlaceIgnoreCase(from, to);
    }


    public Flux<Flight> getAllFlights() {
        return repo.findAll();
    }
}
