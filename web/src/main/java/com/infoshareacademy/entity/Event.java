package com.infoshareacademy.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event", schema = "gwk")
public class Event {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "start_date")
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

}
