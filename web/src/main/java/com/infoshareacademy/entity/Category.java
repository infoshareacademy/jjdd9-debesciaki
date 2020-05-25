package com.infoshareacademy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "root_category_id")
    private Category rootCategory;

    @OneToMany(mappedBy = "category")
    private List<Event> eventList;
}
