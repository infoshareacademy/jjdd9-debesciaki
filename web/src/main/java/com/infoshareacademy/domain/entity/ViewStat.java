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
                query = "SELECT new com.infoshareacademy.domain.view.stat.chart.ClicksPerEvent(v.event.name, COUNT(v)) " +
                        "FROM ViewStat v GROUP BY v.event.name"
        ),
        @NamedQuery(
                name = "ViewStat.periodClicksPerEvent",
                query = "SELECT new com.infoshareacademy.domain.view.stat.chart.ClicksPerOrganizer(v.event.name, COUNT(v)) " +
                        "FROM ViewStat v WHERE (v.viewDate BETWEEN :date1 AND :date2) GROUP BY v.event.name"
        ),
        @NamedQuery(
                name = "ViewStat.globalClicksPerOrganizer",
                query = "SELECT new com.infoshareacademy.domain.view.stat.chart.ClicksPerOrganizer(v.event.organizer.designation, COUNT(v)) " +
                        "FROM ViewStat v GROUP BY v.event.organizer.designation"
        ),
        @NamedQuery(
                name = "ViewStat.periodClicksPerOrganizer",
                query = "SELECT new com.infoshareacademy.domain.view.stat.chart.ClicksPerOrganizer(v.event.organizer.designation, COUNT(v)) " +
                        "FROM ViewStat v WHERE (v.viewDate BETWEEN :date1 AND :date2) GROUP BY v.event.organizer.designation"
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
