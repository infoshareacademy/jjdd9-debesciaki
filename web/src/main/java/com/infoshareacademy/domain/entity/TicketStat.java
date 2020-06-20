package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "TicketStat.findAll",
                query = "SELECT t FROM TicketStat t"
        ),
        @NamedQuery(
                name = "TicketStat.findByEventId",
                query = "SELECT t FROM TicketStat t WHERE t.event.id = :eventId"
        )
})
@Entity
@Table(name = "ticket_stat")
public class TicketStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", unique = true)
    private Event event;

    @Column(name = "full_count")
    private Long fullCount;

    @Column(name = "redu_count")
    private Long reduCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Long getFullCount() {
        return fullCount;
    }

    public void setFullCount(Long fullCount) {
        this.fullCount = fullCount;
    }

    public Long getReduCount() {
        return reduCount;
    }

    public void setReduCount(Long reduCount) {
        this.reduCount = reduCount;
    }
}
