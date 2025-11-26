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
        System.out.println(">>> ACTIVE MONGO DATABASE CHECK");
        System.out.println(">>> Repository type = " + repo.getClass().getName());
        System.out.println(">>> Fetching all flights from MongoDB...");

        return repo.findAll()
                .doOnNext(f -> System.out.println(">>> Found flight: " + f));
    }


    // New helper: get by id
    public Mono<Flight> getFlightById(String id) {
        System.out.println(">>> SEARCHING FLIGHT IN DB FOR ID = " + id);
        return repo.findById(id)
                .doOnNext(f -> System.out.println(">>> FOUND FLIGHT = " + f))
                .switchIfEmpty(Mono.defer(() -> {
                    System.out.println(">>> NO FLIGHT FOUND IN DB!!!");
                    return Mono.empty();
                }));
    }

    // Reserve seats: reduce totalSeats by seats if available
    public Mono<Void> reserveSeats(String flightId, int seats) {
        return repo.findById(flightId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Flight not found: " + flightId)))
                .flatMap(f -> {
                    if (f.getTotalSeats() < seats) {
                        return Mono.error(new IllegalStateException("Insufficient seats"));
                    }
                    f.setTotalSeats(f.getTotalSeats() - seats);
                    return repo.save(f);
                })
                .then();
    }

    // Release seats: increase totalSeats
    public Mono<Void> releaseSeats(String flightId, int seats) {
        return repo.findById(flightId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Flight not found: " + flightId)))
                .flatMap(f -> {
                    f.setTotalSeats(f.getTotalSeats() + seats);
                    return repo.save(f);
                })
                .then();
    }
}
