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

    @GetMapping("/validate/{eventId}")
    public String validateEvent(@PathVariable Long eventId) {
        return bookingService.validateEvent(eventId);
    }
}