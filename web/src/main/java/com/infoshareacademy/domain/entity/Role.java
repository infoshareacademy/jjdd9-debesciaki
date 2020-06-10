package com.infoshareacademy.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@NamedQueries( {
       @NamedQuery(
               name = "Role.findByRole",
               query = "SELECT r FROM Role r WHERE r.name=:role"
       )
})
@Entity
@Table(name = "role")
public class Role {
    @Id
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
