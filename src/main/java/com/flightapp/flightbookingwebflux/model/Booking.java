package com.flightapp.flightbookingwebflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String flightId;

    private String email;

    private int seats;

    private String mealType;
}
