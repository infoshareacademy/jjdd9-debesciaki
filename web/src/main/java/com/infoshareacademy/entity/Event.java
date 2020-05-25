package com.infoshareacademy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToOne(mappedBy = "event")
    private Ticket ticket;

    @OneToOne(mappedBy = "event")
    private Urls urls;

    @OneToMany(mappedBy = "event")
    private List<Attachment> attachments;

    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();


}
