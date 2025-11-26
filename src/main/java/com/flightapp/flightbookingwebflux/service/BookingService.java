package com.flightapp.flightbookingwebflux.service;

import com.flightapp.flightbookingwebflux.model.Booking;
import com.flightapp.flightbookingwebflux.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repo;

    public Mono<Booking> bookFlight(Booking booking) {
        return repo.save(booking);
    }
}
