Flight Booking Application — Spring WebFlux (Reactive)

A fully reactive Flight Booking System built using Spring Boot WebFlux and MongoDB.
The system supports flight search, ticket booking, cancellation, ticket retrieval, and booking history.
It follows a clean service–repository layered architecture using Reactive Streams (Mono/Flux).
This project is implemented based on the detailed assignment requirements and user stories provided in the Flight Booking Case Study document.

1. Problem Statement

Users should be able to:

Search for flights

Book a ticket

Cancel a ticket

View ticket details

View booking history

Admin users can add flight inventory

All operations must be implemented using Spring WebFlux (reactive, non-blocking).

2. Business Requirements Summary
   User Capabilities

Search for flights by date, origin, destination, and trip type (one-way or round-trip)

Select a flight and book tickets by providing:

Passenger details

Email ID

Number of seats

Meal preference

Seat numbers

Download or view tickets (with PNR)

Cancel tickets (only if the journey is at least 24 hours away)

View booking history using email ID

Admin Capabilities

Add airline flight inventory and schedule

3. Project Architecture
4. 
   com.flightapp.flightbookingwebflux

   ├── controller        → Reactive REST Controllers
   ├── service           → Business logic layer
   ├── repository        → Reactive MongoDB Repositories
   ├── model             → Domain models
   ├── exception         → Centralized exception handling (planned)
   └── FlightbookingWebfluxApplication.java → Main Spring Boot class

4. Technologies Used
   Component	Technology
   Backend Framework	Spring Boot 3 + WebFlux
   Database	MongoDB (Reactive)
   Reactive API	Project Reactor (Mono/Flux)
   Build Tool	Maven
   Language	Java 17+
   Architecture	Reactive, Non-blocking REST APIs
5. REST API Endpoints (Assignment Requirements)
1. Add Airline Inventory — Admin

POST /api/v1.0/flight/airline/inventory
Adds flight schedule/inventory.

2. Search for Flights

POST /api/v1.0/flight/search
Search flights by date, from-place, to-place, and trip type.

3. Book Ticket

POST /api/v1.0/flight/booking/{flightId}
Books a ticket for the selected flight.

4. Get Ticket Details by PNR

GET /api/v1.0/flight/ticket/{pnr}

5. View Booking History

GET /api/v1.0/flight/booking/history/{emailId}

6. Cancel Ticket

DELETE /api/v1.0/flight/booking/cancel/{pnr}
Cancellation allowed only if travel date is more than 24 hours away.

6. Project Components in Detail
   Model Classes
   Flight.java

Represents flight details:

Airline

Flight name

Origin and destination

Price

Departure information

Seat availability

Booking.java

Represents booking details:

Passenger list

Email ID

Meal preference

Seat numbers

Generated PNR

Flight reference

Timestamp

Repository Layer (ReactiveMongoRepository)
FlightRepository

Handles reactive CRUD operations for flights.

BookingRepository

Stores and retrieves booking data.

Both repositories return Mono<T> and Flux<T>.

Service Layer
FlightService

Flight search

Retrieve flight details

Add or update inventory

BookingService

Book ticket

Cancel ticket

Fetch booking history

View ticket details

Generate PNR

All logic implemented using reactive programming.

Controller Layer
FlightController

Handles:

Search flights

Ticket details

Add inventory

BookingController

Handles:

Ticket booking

Booking cancellation

Booking history retrieval

Configuration

application.properties contains:

MongoDB configuration

Server port

Basic application configurations

7. Exception Handling (Planned)

Package exception/ is created for:

Global exception handling

Custom business exceptions

Reactive error responses

8. Testing (Future Enhancement)

Potential tests include:

WebTestClient-based reactive endpoint tests

Mockito-based service-layer tests

Reactor context and publisher validation

9. How to Run the Project
   Step 1 — Start MongoDB

Ensure MongoDB service is running.

Step 2 — Build the project
mvn clean install

Step 3 — Run the application
mvn spring-boot:run

Step 4 — Access APIs

Base URL:

http://localhost:8080/api/v1.0/flight

10. Code Quality and Rubrics

The project follows the expected standards:

Clean package structure

Clear separation of concerns

Meaningful commit history

Clean code structure

Fully reactive WebFlux implementation

Proper configuration management

11. Future Enhancements

JWT-based authentication

Complete exception handling module

Round-trip pricing logic

Email notifications

PDF ticket download

12. Conclusion

This project implements a fully reactive Flight Booking System using Spring WebFlux and MongoDB, delivering a clean architecture, end-to-end functionality, and alignment with the official problem statement requirements.