package com.infoshareacademy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "file_name")
    @NotNull
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
