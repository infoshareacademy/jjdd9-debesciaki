package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "Event.findAll",
                query = "SELECT e FROM Event e ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByEveOrg",
                query = "SELECT e FROM Event e WHERE (e.name LIKE :phrase) OR (e.organizer.designation LIKE :phrase) ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByEveOrgDate",
                query = "SELECT e FROM Event e WHERE ((e.name LIKE :phrase) OR(e.organizer.designation LIKE :phrase)) " +
                        "AND (e.startDate  BETWEEN :start AND :end) AND (e.endDate BETWEEN :start AND :end) ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByEve",
                query = "SELECT e FROM Event e WHERE e.name LIKE :phrase ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByEveDate",
                query = "SELECT e FROM Event e WHERE (e.name LIKE :phrase) AND (e.startDate  BETWEEN :start AND :end) " +
                        "AND (e.endDate BETWEEN :start AND :end) ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByOrg",
                query = "SELECT e FROM Event e WHERE e.organizer.designation LIKE :phrase ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByOrgDate",
                query = "SELECT e FROM Event e WHERE (e.organizer.designation LIKE :phrase) " +
                        "AND (e.startDate  BETWEEN :start AND :end) AND (e.endDate BETWEEN :start AND :end) ORDER BY e.startDate ASC"
        ),
        @NamedQuery(
                name = "Event.findByApiId",
                query = "SELECT e FROM Event e WHERE e.apiId = :apiID"
        ),
        @NamedQuery(
                name = "Event.findFinished",
                query = "SELECT e FROM Event e WHERE e.endDate < :now"
        ),
})
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", unique = true)
    private Long apiId;

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
    private Integer active;

    @Column(name = "desc_long", length = 10000)
    private String descLong;

    @Column(name = "desc_short")
    private String descShort;

    @Column(name = "ticket_amount")
    private Long ticketAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", unique = true)
    private Ticket ticket;

    @OneToOne(mappedBy = "event")
    private TicketStat ticketStat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "urls_id", unique = true)
    private Urls urls;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getActive() {
        return active;
    }


    public String getDescLong() {
        return descLong;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public TicketStat getTicketStat() {
        return ticketStat;
    }

    public void setTicketStat(TicketStat ticketStat) {
        this.ticketStat = ticketStat;
    }

    public Long getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Long ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
}
