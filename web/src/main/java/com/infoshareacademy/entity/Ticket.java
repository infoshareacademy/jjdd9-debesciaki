package com.infoshareacademy.entity;

import javax.persistence.*;

@Entity
@Table(name = "place")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "start_ticket")
    private Integer startTicket;

    @Column(name = "end_ticket")
    private Integer endTicket;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
