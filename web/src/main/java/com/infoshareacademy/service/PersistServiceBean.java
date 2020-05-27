package com.infoshareacademy.service;

import com.infoshareacademy.writerRepository.CategoryPersisterBean;
import com.infoshareacademy.writerRepository.EventPersisterBean;
import com.infoshareacademy.writerRepository.OrganizerPersisterBean;
import com.infoshareacademy.writerRepository.PlacePersisterBean;

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
        organizerPersisterBean.persistEntityList(fileToDaoBean.placeList(file));
    }
}
