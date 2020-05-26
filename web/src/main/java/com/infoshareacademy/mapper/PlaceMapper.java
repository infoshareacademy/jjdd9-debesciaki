package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.PlaceJSON;
import com.infoshareacademy.entityDAO.Place;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlaceMapper {
    @Inject
    AddressMapper addressMapper;

    public PlaceJSON daoToJson(Place place) {
        PlaceJSON jsonPlace = new PlaceJSON();
        jsonPlace.setId(place.getId());
        jsonPlace.setAddress(addressMapper.daoToJson(place.getAddress()));
        jsonPlace.setName(place.getName());
        jsonPlace.setSubname(place.getSubname());
        return jsonPlace;
    }

    public Place jsonToDao(PlaceJSON place) {
        Place daoPlace = new Place();
        daoPlace.setId(place.getId());
        daoPlace.setAddress(addressMapper.jsonToDao(place.getAddress()));
        daoPlace.setName(place.getName());
        daoPlace.setSubname(place.getSubname());
        return daoPlace;
    }
}
