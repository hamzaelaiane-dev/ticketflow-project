package com.ticketflow.bookingservice.controllers;

import com.ticketflow.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Le Booking Service est vivant V2 !";
    }

    // On remet en GET pour tester facilement dans le navigateur
    @GetMapping("/validate/{eventId}/{customerId}")
    public String validateEvent(@PathVariable Long eventId, @PathVariable Long customerId) {
        try {
            return bookingService.validateAndBook(eventId, customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERREUR : " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
    }
}