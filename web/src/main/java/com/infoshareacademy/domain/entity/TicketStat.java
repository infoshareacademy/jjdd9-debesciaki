package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "TicketStat.findFull",
                query = "SELECT  new com.infoshareacademy.domain.stat.TicketCount(t.event.name, t.ticketType, count(t)) FROM TicketStat t " +
                        "WHERE (t.ticketType='full') GROUP BY t.event.name, t.ticketType "
        ),
        @NamedQuery(
                name = "TicketStat.findRedu",
                query = "SELECT  new com.infoshareacademy.domain.stat.TicketCount(t.event.name, t.ticketType, count(t)) FROM TicketStat t " +
                        "WHERE (t.ticketType='redu') GROUP BY t.event.name, t.ticketType "
        )
})
@Entity
@Table(name = "ticket_stat")
public class TicketStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ticket_type")
    private String ticketType;


    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
