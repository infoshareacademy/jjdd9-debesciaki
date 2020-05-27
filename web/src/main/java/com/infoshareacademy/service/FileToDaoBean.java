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
        return listJsonToDaoBean.place(fileToJsonList.fileToJsonList(file, PlaceJSON.class));
    }

    public List<Event> eventList(File file) throws IOException {
        return listJsonToDaoBean.event(fileToJsonList.fileToJsonList(file, EventJSON.class));
    }

    public List<Organizer> organizerList(File file) throws IOException {
        return listJsonToDaoBean.organizer(fileToJsonList.fileToJsonList(file, OrganizerJSON.class));
    }

    public List<Category> categoryList(File file) throws IOException {
        return listJsonToDaoBean.category(fileToJsonList.fileToJsonList(file, CategoryJSON.class));
    }

}
