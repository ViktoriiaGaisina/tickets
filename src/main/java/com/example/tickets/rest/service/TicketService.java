package com.example.tickets.rest.service;

import com.example.tickets.dto.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket();

    TicketDTO getCurrentTicketByNumber(Long number);

    void deleteLastTicket();

    List<TicketDTO> getAllActiveTickets();

}
