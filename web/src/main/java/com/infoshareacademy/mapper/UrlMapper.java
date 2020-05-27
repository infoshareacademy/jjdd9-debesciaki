package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.UrlJSON;
import com.infoshareacademy.domain.entity.Urls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class UrlMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(UrlMapper.class.getName());

    public UrlJSON daoToJson(Urls url) {
        UrlJSON jsonUrl = new UrlJSON();
        jsonUrl.setWww(url.getWww());
        jsonUrl.setFb(url.getFb());
        jsonUrl.setTickets(url.getTickets());
        STDLOG.info("Success in mapping dao to json");
        return jsonUrl;
    }

    public Urls jsonToDao(Urls url) {
        Urls daoUrl = new Urls();
        daoUrl.setWww(url.getWww());
        daoUrl.setFb(url.getFb());
        daoUrl.setTickets(url.getTickets());
        STDLOG.info("Success in mapping json to dao");
        return daoUrl;
    }

}
