package com.example.tickets.mapper;

import com.example.tickets.data.Ticket;
import com.example.tickets.dto.TicketDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDTO toDto(Ticket tickets);

    Ticket toEntity(TicketDTO dto);

    List<TicketDTO> toDto(List<Ticket> tickets);

    List<Ticket> toEntity(List<TicketDTO> tickets);
}
