package com.infoshareacademy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "organizer")
public class Organizer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "designation")
    @NotNull
    private String designation;

    @OneToMany(mappedBy = "organizer")
    private List<Event> eventList;

}
