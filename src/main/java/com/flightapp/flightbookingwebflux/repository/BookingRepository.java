package com.flightapp.flightbookingwebflux.repository;

import com.flightapp.flightbookingwebflux.model.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
}
