package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "TicketStat.findAll",
                query = "SELECT t FROM TicketStat t"
        ),
        /*@NamedQuery(
                name = "TicketStat.findFull",
                query = "SELECT  new com.infoshareacademy.domain.stat.TicketCount(t.event.name, t.ticketType, count(t)) FROM TicketStat t " +
                        "WHERE (t.ticketType='full') GROUP BY t.event.name, t.ticketType "
        ),
        @NamedQuery(
                name = "TicketStat.findRedu",
                query = "SELECT  new com.infoshareacademy.domain.stat.TicketCount(t.event.name, t.ticketType, count(t)) FROM TicketStat t " +
                        "WHERE (t.ticketType='redu') GROUP BY t.event.name, t.ticketType "
        )*/
})
@Entity
@Table(name = "ticket_stat")
public class TicketStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "full_count", nullable = false)
    private Long fullCount;

    @Column(name = "redu_count", nullable = false)
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
