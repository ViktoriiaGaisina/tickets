package com.example.tickets.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketDTO {
    private Long number;
    private LocalDateTime localDateTime;
    private int numberWait;
}
