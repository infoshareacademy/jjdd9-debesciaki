package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.api.PlaceJSON;
import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.domain.entity.Place;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class FileToDaoBean {
    @Inject
    FileToJsonList fileToJsonList;
    @Inject
    ListJsonToDaoBean listJsonToDaoBean;

    public List<Place> placeList(File file) throws IOException {
        return listJsonToDaoBean.place(fileToJsonList.place(file));
    }

    public List<Event> eventList(File file) throws IOException {
        return listJsonToDaoBean.event(fileToJsonList.event(file));
    }

    public List<Organizer> organizerList(File file) throws IOException {
        return listJsonToDaoBean.organizer(fileToJsonList.organizer(file));
    }

    public List<Category> categoryList(File file) throws IOException {
        return listJsonToDaoBean.category(fileToJsonList.category(file));
    }

}
