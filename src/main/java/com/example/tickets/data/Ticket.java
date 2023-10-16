package com.example.tickets.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(name = "date", nullable = false)
    private LocalDateTime localDateTime = LocalDateTime.now();

    @Column(name = "number_wait", nullable = false)
    private int numberWait;
}
