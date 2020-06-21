package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@NamedQueries({
        @NamedQuery(
                name = "Category.findByApiId",
                query = "SELECT c FROM Category c WHERE c.apiId=:apiID"
        ),
        @NamedQuery(
                name = "Category.findByName",
                query = "SELECT c FROM Category c WHERE c.name=:name"
        ),
        @NamedQuery(
                name = "Category.findByEventId",
                query = "SELECT c FROM Category c WHERE (SELECT e FROM Event e WHERE e.id=:eventId) MEMBER OF c.eventList"
        )
})
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private Long apiId;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_category_id")
    private Category rootCategory;

    @OneToMany(mappedBy = "rootCategory",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories= new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Event> eventList;

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

    public Category getRootCategory() {
        return rootCategory;
    }

    public void setRootCategory(Category rootCategory) {
        this.rootCategory = rootCategory;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
