package com.infoshareacademy.entity;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class Urls {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "www")
    private String www;

    @Column(name = "tickets")
    private String tickets;

    @Column(name = "fb")
    private String fb;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

}
