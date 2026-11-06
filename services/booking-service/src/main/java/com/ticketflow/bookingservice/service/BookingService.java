package com.ticketflow.bookingservice.service;

import com.ticketflow.bookingservice.clients.EventClient;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final EventClient eventClient;

    public BookingService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public String validateEvent(Long eventId) {
        Object event = eventClient.getById(eventId);
        return "Réservation validée pour l'événement : " + eventId + ". Détails : " + event.toString();
    }
}