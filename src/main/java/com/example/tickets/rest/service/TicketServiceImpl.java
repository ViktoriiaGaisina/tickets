package com.example.tickets.rest.service;

import com.example.tickets.data.Ticket;
import com.example.tickets.dto.TicketDTO;
import com.example.tickets.mapper.TicketMapper;
import com.example.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public TicketDTO createTicket() {
        Ticket ticket = new Ticket();
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDTO getCurrentTicketByNumber(Long number) {
        Ticket ticket = ticketRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException());
        return ticketMapper.toDto(ticket);
    }

    @Override
    public void deleteLastTicket() {
        Ticket ticket = ticketRepository.findTopByOrderByNumberDesc();
            ticketRepository.delete(ticket);
    }

    @Override
    public List<TicketDTO> getAllActiveTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
}