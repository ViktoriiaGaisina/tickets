package com.example.tickets.repository;

import com.example.tickets.data.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByNumber(Long number);
    Ticket findTopByOrderByNumberDesc();
}