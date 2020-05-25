package com.infoshareacademy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "end_date")
    @NotNull
    private LocalDateTime endDate;

    @Column(name = "start_date")
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "active")
    private int active;

    @Column(name = "desc_long")
    private String descLong;

    @Column(name = "desc_short")
    private String descShort;

    @Column(name = "tickets")
    private String tickets;

    @Column(name = "urls")
    private String urls;

    @Column(name = "attachments")
    private String attachments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
