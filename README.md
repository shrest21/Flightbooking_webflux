Flight Booking System (Spring Boot WebFlux + Reactive MongoDB)

A fully reactive Flight Search + Booking System built using:

Spring Boot 4

Spring WebFlux

Reactive MongoDB

Project Reactor (Mono/Flux)

This application supports:

✔ Add Flights
✔ Search Flights
✔ View All Flights
✔ Book Tickets
✔ Get Ticket by PNR
✔ Booking History by Email
✔ Cancel Ticket
✔ View All Bookings

All features are 100% reactive, non-blocking, and run on top of Netty.

🚀 Features Implemented
✈️ Flight Management

Add a new flight

View list of all flights

Search flights by source & destination

Automatic seat management (reserve & release seats)

🎫 Booking Management

Book tickets with seat reservation

Generate unique PNR

Get ticket details by PNR

Email-based booking history

Cancel booking and restore seats

Store passengers + meal preferences

💾 Fully Reactive Data Access

Using ReactiveMongoRepository

High performance, scalable, non-blocking I/O

🛠️ Technologies Used
Tech	Version
Java	17 / 22
Spring Boot	4.0.0
Spring WebFlux	Reactive
MongoDB	Reactive Driver
Maven	Build Tool
Netty	Reactive HTTP Server
📁 Project Structure
src/main/java/com/flightapp/flightbookingwebflux
│
├── controller
│   ├── FlightController.java
│   └── BookingController.java
│
├── service
│   ├── FlightService.java
│   └── BookingService.java
│
├── repository
│   ├── FlightRepository.java
│   └── BookingRepository.java
│
├── dto
│   └── BookingRequest.java
│
├── model
│   ├── Flight.java
│   ├── Booking.java
│   ├── Passenger.java
│   └── BookingStatus.java
│
└── util
└── PnrGenerator.java

⚙️ Run & Setup Instructions
1️⃣ Start MongoDB

Make sure MongoDB is running:

mongod

2️⃣ Update application.properties
spring.application.name=flightbooking-webflux
spring.data.mongodb.uri=mongodb://localhost:27017/flightdb
server.port=8081

3️⃣ Build & Run the App
mvn clean package
mvn spring-boot:run


Server starts at:

http://localhost:8081

📌 API Endpoints (Complete List)
✈️ FLIGHT APIs
➕ 1. Add Flight

POST /flights

Request Body
{
"airline": "IndiGo",
"fromPlace": "DELHI",
"toPlace": "MUMBAI",
"flightName": "6E-501",
"departureTime": "10:00",
"arrivalTime": "12:00",
"flightDate": "2025-12-01",
"totalSeats": 180,
"price": 4500
}

Response
{
"id": "6927f6e73dcccdcd1cedf2",
"airline": "IndiGo",
"fromPlace": "DELHI",
"toPlace": "MUMBAI",
"flightName": "6E-501",
"departureTime": "10:00",
"arrivalTime": "12:00",
"flightDate": "2025-12-01",
"totalSeats": 180,
"price": 4500
}

🔍 2. Search Flights

GET /flights/search?from=DELHI&to=MUMBAI

Response
[
{
"id": "6927f6e73dcccdcd1cedf2",
"airline": "IndiGo",
"fromPlace": "DELHI",
"toPlace": "MUMBAI",
"flightName": "6E-501",
"departureTime": "10:00",
"arrivalTime": "12:00",
"flightDate": "2025-12-01",
"totalSeats": 180,
"price": 4500
}
]

📄 3. View All Flights

GET /flights

🎫 BOOKING APIs
🟢 4. Book a Ticket

POST /booking

Request Body
{
"flightId": "69277d58576ca4d0b017c4848",
"name": "Shrest",
"email": "shrest@test.com",
"seats": 2,
"mealType": "VEG"
}

Response
{
"id": "692779705e03a36d36495b223",
"pnr": "E8CY6JCX",
"flightId": "69277d58576ca4d0b017c4848",
"name": "Shrest",
"email": "shrest@test.com",
"seats": 2,
"mealType": "VEG",
"status": "BOOKED",
"totalPrice": 9000,
"journeyDate": "2025-12-01T10:00+05:30[Asia/Kolkata]"
}

🎟 5. Get Ticket by PNR

GET /booking/ticket/{pnr}

📬 6. Booking History

GET /booking/history/{email}

❌ 7. Cancel Ticket

DELETE /booking/cancel/{pnr}

Response
{
"status": "CANCELLED",
"pnr": "E8CY6JCX"
}

📑 8. View All Bookings

GET /booking

🧠 Seat Management Logic
On Booking:
totalSeats = totalSeats - requestedSeats

On Cancellation:
totalSeats = totalSeats + cancelledSeats