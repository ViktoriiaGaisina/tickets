package com.example.tickets.rest.service;

import com.example.tickets.TicketApplication;
import com.example.tickets.data.Ticket;
import com.example.tickets.dto.TicketDTO;
import com.example.tickets.mapper.TicketsMapper;
import com.example.tickets.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest(classes = TicketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketsMapper ticketsMapper;

    @AfterEach
    public void cleanUpEach() {
        ticketRepository.deleteAll();
    }

    @Test
    void testCreateTicket() {
        TicketDTO ticketDTO = createDTO();
        TicketDTO createdTicket = ticketService.createTicket(ticketsMapper.toEntity(ticketDTO));
        Assertions.assertNotNull(createdTicket);
    }

    @Test
    void testGetAllActiveTicketsReturnsAllSavedTickets() {
        TicketDTO ticketDTO1 = createDTO();
        TicketDTO ticketDTO2 = createDTO();
        ticketService.createTicket(ticketsMapper.toEntity(ticketDTO1));
        ticketService.createTicket(ticketsMapper.toEntity(ticketDTO2));
        List<TicketDTO> tickets = ticketService.getAllActiveTickets().stream()
                .map(ticketsMapper::toDto)
                .collect(Collectors.toList());
        Assertions.assertEquals(2, tickets.size());
    }

    @Test
    void testGetCurrentTicketByNumber() {
        TicketDTO ticketDTO = createDTO();
        Ticket ticket = ticketService.createTicket(ticketsMapper.toEntity(ticketDTO));
        TicketDTO foundTicket = ticketsMapper.toDto(ticketService.getCurrentTicketByNumber(Math.toIntExact(ticket.getNumber())));
        Assertions.assertEquals(ticketDTO.getNumber(), foundTicket.getNumber());
    }

    @Test
    void testDeleteLastTicket() {
        TicketDTO ticketDTO1 = createDTO();
        TicketDTO ticketDTO2 = createDTO();
        ticketService.createTicket(ticketsMapper.toEntity(ticketDTO1));
        ticketService.createTicket(ticketsMapper.toEntity(ticketDTO2));
        ticketService.deleteLastTicket();
        List<TicketDTO> tickets = ticketService.getAllActiveTickets().stream()
                .map(ticketsMapper::toDto)
                .collect(Collectors.toList());
        Assertions.assertEquals(1, tickets.size());
    }

    private TicketDTO createDTO() {
        return TicketDTO.builder()
                .number(1L)
                .localDateTime(LocalDateTime.now())
                .numberWait(1)
                .build();
    }
}