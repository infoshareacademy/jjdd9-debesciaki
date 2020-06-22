package com.infoshareacademy.service;

import com.infoshareacademy.domain.entity.Address;
import com.infoshareacademy.domain.entity.Place;
import com.infoshareacademy.domain.view.PlaceView;
import com.infoshareacademy.repository.PlaceDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PlaceViewService {

    @Inject
    private PlaceDao placeDao;

    public Place mapper(PlaceView placeView) {
        Place place = new Place();
        place.setName(placeView.getName());
        place.setSubname(placeView.getSubname());
        return place;
    }

}
