package com.flightapp.flightbookingwebflux.controller;

import com.flightapp.flightbookingwebflux.model.Flight;
import com.flightapp.flightbookingwebflux.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {

    private final FlightService service;

    @PostMapping
    public Mono<Flight> add(@RequestBody Flight flight) {
        return service.addFlight(flight);
    }

    @GetMapping("/search")
    public Flux<Flight> search(@RequestParam String from,
                               @RequestParam String to) {
        return service.search(from, to);
    }

    @GetMapping
    public Flux<Flight> all() {
        return service.getAllFlights();
    }
}
