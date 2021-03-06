package com.infoshareacademy.domain.entity;

import jdk.jfr.Name;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Place.findByApiId",
                query = "SELECT p FROM Place p WHERE p.apiId=:apiID"
        ),
        @NamedQuery(
                name = "Place.findByNameAndSubname",
                query = "SELECT p FROM Place p WHERE p.name=:name AND p.subname=:subname"
        ),
        @NamedQuery(
                name = "Place.findAll",
                query = "SELECT p FROM Place p"
        ),
        @NamedQuery(
                name = "Place.findDistinctNames",
                query = "SELECT DISTINCT p.name FROM Place p"
        ),
        @NamedQuery(
                name = "Place.findByEventId",
                query = "SELECT p FROM Place p WHERE (SELECT e FROM Event e WHERE e.id=:eventId) MEMBER OF p.eventList"
        )
})
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", unique = true)
    private Long apiId;

    @Column(name = "name")
    private String name;

    @Column(name = "subname")
    private String subname;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToMany(mappedBy = "place")
    List<Event> eventList;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
