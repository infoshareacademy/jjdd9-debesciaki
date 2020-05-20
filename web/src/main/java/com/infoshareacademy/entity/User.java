package com.infoshareacademy.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "gwk")
public class User implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "mail")
    private String mail;
    @Column(name = "role_id")
    private int roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
