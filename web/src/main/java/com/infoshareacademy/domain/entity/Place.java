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
                name = "Place.findByName",
                query = "SELECT p FROM Place p WHERE p.name=:name"
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
