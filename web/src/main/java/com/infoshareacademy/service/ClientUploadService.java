package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.api.PlaceJSON;
import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.Event;
import com.infoshareacademy.domain.entity.Organizer;
import com.infoshareacademy.domain.entity.Place;
import com.infoshareacademy.repository.writer.CategoryPersisterBean;
import com.infoshareacademy.repository.writer.EventPersisterBean;
import com.infoshareacademy.repository.writer.OrganizerPersisterBean;
import com.infoshareacademy.repository.writer.PlacePersisterBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Stateless
public class ClientUploadService {
    private static final Logger STDLOG = LoggerFactory.getLogger(ClientUploadService.class.getName());

    @Inject
    FileToJsonList fileToJsonList;
    @Inject
    ListJsonToDaoBean listJsonToDaoBean;
    @Inject
    PlacePersisterBean placePersisterBean;
    @Inject
    EventPersisterBean eventPersisterBean;
    @Inject
    OrganizerPersisterBean organizerPersisterBean;
    @Inject
    CategoryPersisterBean categoryPersisterBean;

    public void upload() throws IOException {
        List<CategoryJSON> categoryJSON = parseCategoryFromURL("https://planerkulturalny.pl/api/rest/categories.json");
        List<Category> category = listJsonToDaoBean.category(categoryJSON);
        categoryPersisterBean.persistEntityList(category);

        List<OrganizerJSON> organizerJSON = parseOrganizerFromURL("https://planerkulturalny.pl/api/rest/organizers.json");
        List<Organizer> organizer = listJsonToDaoBean.organizer(organizerJSON);
        organizerPersisterBean.persistEntityList(organizer);

        List<PlaceJSON> placeJSON = parsePlaceFromURL("https://planerkulturalny.pl/api/rest/places.json");
        List<Place> place = listJsonToDaoBean.place(placeJSON);
        placePersisterBean.persistEntityList(place);

        List<EventJSON> eventJSON = parseEventFromURL("https://planerkulturalny.pl/api/rest/events.json");
        List<Event> event = listJsonToDaoBean.event(eventJSON);
        eventPersisterBean.persistEntityList(event);
    }

    public List<OrganizerJSON> parseOrganizerFromURL(String urlPath) throws IOException {
        STDLOG.info("Connecting to url API for JSON DATA {};", "Organizers");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlPath);

        int status = 0;

        try {
            Response response = target.request().get();
            status = response.getStatus();
        } catch (Exception e) {
            STDLOG.debug("Service is not responding; {}", e);
        }
        if (status != 200) {
            STDLOG.debug("No valid url or API is down! status {}", status);
        }

        return fileToJsonList.organizer(new URL(urlPath));
    }

    public List<PlaceJSON> parsePlaceFromURL(String urlPath) throws IOException {
        STDLOG.info("Connecting to url API for JSON DATA {};", "Places");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlPath);

        int status = 0;

        try {
            Response response = target.request().get();
            status = response.getStatus();
        } catch (Exception e) {
            STDLOG.debug("Service is not responding; {}", e);
        }
        if (status != 200) {
            STDLOG.debug("No valid url or API is down! status {}", status);
        }

        return fileToJsonList.place(new URL(urlPath));
    }

    public List<CategoryJSON> parseCategoryFromURL(String urlPath) throws IOException {
        STDLOG.info("Connecting to url API for JSON DATA {};", "Categories");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlPath);

        int status = 0;

        try {
            Response response = target.request().get();
            status = response.getStatus();
        } catch (Exception e) {
            STDLOG.debug("Service is not responding; {}", e);
        }
        if (status != 200) {
            STDLOG.debug("No valid url or API is down! status {}", status);
        }

        return fileToJsonList.category(new URL(urlPath));
    }

    public List<EventJSON> parseEventFromURL(String urlPath) throws IOException {
        STDLOG.info("Connecting to url API for JSON DATA {};", "Events");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlPath);

        int status = 0;

        try {
            Response response = target.request().get();
            status = response.getStatus();
        } catch (Exception e) {
            STDLOG.debug("Service is not responding; {}", e);
        }
        if (status != 200) {
            STDLOG.debug("No valid url or API is down! status {}", status);
        }

        return fileToJsonList.event(new URL(urlPath));
    }
}
