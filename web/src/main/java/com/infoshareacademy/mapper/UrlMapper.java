package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.UrlJSON;
import com.infoshareacademy.entityDAO.Urls;

import javax.ejb.Stateless;

@Stateless
public class UrlMapper {

    public UrlJSON daoToJson(Urls url) {
        UrlJSON jsonUrl = new UrlJSON();
        jsonUrl.setWww(url.getWww());
        jsonUrl.setFb(url.getFb());
        jsonUrl.setTickets(url.getTickets());
        return jsonUrl;
    }

    public Urls jsonToDao(Urls url) {
        Urls daoUrl = new Urls();
        daoUrl.setWww(url.getWww());
        daoUrl.setFb(url.getFb());
        daoUrl.setTickets(url.getTickets());
        return daoUrl;
    }
}
