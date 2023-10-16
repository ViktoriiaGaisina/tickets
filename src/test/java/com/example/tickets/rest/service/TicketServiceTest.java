package com.example.tickets.rest.service;

import com.example.tickets.TicketApplication;
import com.example.tickets.data.Ticket;
import com.example.tickets.dto.TicketDTO;
import com.example.tickets.mapper.TicketMapper;
import com.example.tickets.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;


@ActiveProfiles("test")
@SpringBootTest(classes = TicketApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @AfterEach
    public void cleanUpEach() {
        ticketRepository.deleteAll();
    }

    @Test
    void testCreateTicket() {
        TicketDTO ticketDTO = createDTO();
        Ticket ticket = ticketRepository.save(ticketMapper.toEntity(ticketDTO));
        Assertions.assertNotNull(ticket);
    }

    @Test
    void testGetAllActiveTicketsReturnsAllSavedTickets() {
        TicketDTO ticketDTO1 = createDTO();
        TicketDTO ticketDTO2 = createDTO();
        Ticket ticket1 = ticketRepository.save(ticketMapper.toEntity(ticketDTO1));
        Ticket ticket2 = ticketRepository.save(ticketMapper.toEntity(ticketDTO2));
        List<TicketDTO> tickets = ticketService.getAllActiveTickets();
        Assertions.assertEquals(2, tickets.size());
    }

    @Test
    void testGetCurrentTicketByNumber() {
        TicketDTO ticketDTO = createDTO();
        Ticket ticket1 = ticketRepository.save(ticketMapper.toEntity(ticketDTO));
        TicketDTO foundTicket = ticketService.getCurrentTicketByNumber(ticket1.getNumber());
        Assertions.assertNotNull(foundTicket);
    }

    @Test
    void testDeleteLastTicket() {
        TicketDTO ticketDTO1 = createDTO();
        TicketDTO ticketDTO2 = createDTO();
        Ticket ticket1 = ticketRepository.save(ticketMapper.toEntity(ticketDTO1));
        Ticket ticket2 = ticketRepository.save(ticketMapper.toEntity(ticketDTO2));
        ticketService.deleteLastTicket();
        List<TicketDTO> tickets = ticketService.getAllActiveTickets();
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