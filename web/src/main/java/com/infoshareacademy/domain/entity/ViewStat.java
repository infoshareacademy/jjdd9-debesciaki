package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "ViewStat.findAll",
                query = "SELECT v FROM ViewStat v"
        ),
        @NamedQuery(
                name = "ViewStat.globalClicksPerEvent",
                query = "SELECT v.event.name AS event_name, COUNT(v) AS count " +
                        "FROM ViewStat v " +
                        "GROUP BY v.event.name"
        )
})
@Entity
@Table(name = "view_stat")
public class ViewStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view_date")
    @NotNull
    private LocalDateTime viewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDateTime viewDate) {
        this.viewDate = viewDate;
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
