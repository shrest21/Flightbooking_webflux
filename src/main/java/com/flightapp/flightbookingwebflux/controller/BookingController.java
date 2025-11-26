package com.flightapp.flightbookingwebflux.controller;

import com.flightapp.flightbookingwebflux.model.Booking;
import com.flightapp.flightbookingwebflux.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService service;

    @PostMapping
    public Mono<Booking> book(@RequestBody Booking booking) {
        return service.bookFlight(booking);
    }
}
