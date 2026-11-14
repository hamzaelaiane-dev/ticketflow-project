package com.ticketflow.bookingservice.service;

import com.ticketflow.bookingservice.clients.CustomerClient;
import com.ticketflow.bookingservice.clients.EventClient;
import com.ticketflow.bookingservice.entities.Booking;
import com.ticketflow.bookingservice.repos.BookingRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BookingService {
    private final EventClient eventClient;
    private final CustomerClient customerClient;
    private final BookingRepository bookingRepository;

    public BookingService(EventClient eventClient, CustomerClient customerClient, BookingRepository bookingRepository) {
        this.eventClient = eventClient;
        this.customerClient = customerClient;
        this.bookingRepository = bookingRepository;
    }

    public String validateAndBook(Long eventId, Long customerId) {
        // 1. Vérifier que l'événement existe
        Object event = eventClient.getById(eventId);

        // 2. Vérifier que le client existe (NOUVEAU !)
        Object customer = customerClient.getById(customerId);

        // 3. Créer la réservation
        Booking booking = new Booking();
        booking.setEventId(eventId);
        booking.setCustomerId(customerId);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");

        // 4. Sauvegarder dans MySQL
        bookingRepository.save(booking);

        return "Réservation confirmée pour l'événement " + eventId + " et le client " + customerId;
    }
}