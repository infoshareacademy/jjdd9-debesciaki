package com.infoshareacademy.service;

import com.infoshareacademy.repository.CategoryDao;
import com.infoshareacademy.repository.EventDao;
import com.infoshareacademy.repository.OrganizerDao;
import com.infoshareacademy.repository.PlaceDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@Stateless
public class PersistServiceBean {
    @Inject
    private FileToDaoBean fileToDaoBean;

    @Inject
    private PlaceDao placeDao;

    @Inject
    private EventDao eventDao;

    @Inject
    private OrganizerDao organizerDao;

    @Inject
    private CategoryDao categoryDao;

    public void place(File file) throws IOException {
        placeDao.persistEntityList(fileToDaoBean.placeList(file));
    }

    public void organizer(File file) throws IOException {
        organizerDao.persistEntityList(fileToDaoBean.organizerList(file));
    }

    public void category(File file) throws IOException {
        categoryDao.persistEntityList(fileToDaoBean.categoryList(file));
    }

    public void event(File file) throws IOException {
        eventDao.persistEntityList(fileToDaoBean.eventList(file));
    }
}
