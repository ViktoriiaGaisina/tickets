package com.example.tickets.controller;

import com.example.tickets.dto.TicketDTO;
import com.example.tickets.rest.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket() {
        TicketDTO ticketDTO = ticketService.createTicket();
        return ResponseEntity.ok(ticketDTO);
    }

    @GetMapping("/{number}")
    public ResponseEntity<TicketDTO> getCurrentTicket(@PathVariable Long number) {
        TicketDTO ticketDTO = ticketService.getCurrentTicketByNumber(number);
        return ResponseEntity.ok(ticketDTO);
    }

    @DeleteMapping("/last")
    public ResponseEntity<Void> deleteLastTicket() {
        ticketService.deleteLastTicket();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllActiveTickets() {
        List<TicketDTO> tickets = ticketService.getAllActiveTickets();
        return ResponseEntity.ok(tickets);
    }
}
