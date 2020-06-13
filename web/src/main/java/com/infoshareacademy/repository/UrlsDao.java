package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Place;
import com.infoshareacademy.domain.entity.Urls;
import com.infoshareacademy.domain.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Stateless
public class UrlsDao {
    @PersistenceContext
    EntityManager entityManager;

    public Urls save(String www) {
        Urls url = new Urls();
        url.setWww(www);
        entityManager.persist(url);
        return url;
    }
}
