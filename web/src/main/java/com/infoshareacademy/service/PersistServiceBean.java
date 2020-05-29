package com.infoshareacademy.service;

import com.infoshareacademy.repository.writer.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@Stateless
public class PersistServiceBean {
    @Inject
    FileToDaoBean fileToDaoBean;
    @Inject
    PlacePersisterBean placePersisterBean;
    @Inject
    EventPersisterBean eventPersisterBean;
    @Inject
    OrganizerPersisterBean organizerPersisterBean;
    @Inject
    CategoryPersisterBean categoryPersisterBean;

    public void place(File file) throws IOException {
        placePersisterBean.persistEntityList(fileToDaoBean.placeList(file));
    }

    public void organizer(File file) throws IOException {
        organizerPersisterBean.persistEntityList(fileToDaoBean.organizerList(file));
    }

    public void category(File file) throws IOException {
        categoryPersisterBean.persistEntityList(fileToDaoBean.categoryList(file));
    }

    public void event(File file) throws IOException {
        eventPersisterBean.persistEntityList(fileToDaoBean.eventList(file));
    }
}
