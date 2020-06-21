package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.PlaceJSON;
import com.infoshareacademy.domain.entity.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlaceMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(PlaceMapper.class.getName());

    @Inject
    private AddressMapper addressMapper;

    public Place jsonToDao(PlaceJSON place) {
        Place daoPlace = new Place();
        daoPlace.setApiId(place.getId());
        daoPlace.setAddress(addressMapper.jsonToDao(place.getAddress()));
        daoPlace.setName(place.getName());
        daoPlace.setSubname(place.getSubname());
        STDLOG.info("Success in mapping json to dao");
        return daoPlace;
    }
}
