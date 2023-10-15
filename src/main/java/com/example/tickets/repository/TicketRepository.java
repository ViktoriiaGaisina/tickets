package com.example.tickets.repository;

import com.example.tickets.data.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByNumber(int number);
    Ticket findTopByOrderByNumberDesc();
}