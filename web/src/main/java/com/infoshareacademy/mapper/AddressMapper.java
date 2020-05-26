package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.AddressJSON;
import com.infoshareacademy.entityDAO.Address;

import javax.ejb.Stateless;

@Stateless
public class AddressMapper {
    public AddressJSON daoToJson(Address address) {
        AddressJSON jsonAddress = new AddressJSON();
        jsonAddress.setCity(address.getCity());
        jsonAddress.setLat(address.getLat());
        jsonAddress.setLng(address.getLng());
        jsonAddress.setStreet(address.getStreet());
        jsonAddress.setZipcode(address.getZipcode());
        return jsonAddress;
    }

    public Address jsonToDao(AddressJSON address) {
        Address daoAddress = new Address();
        daoAddress.setCity(address.getCity());
        daoAddress.setLat(address.getLat());
        daoAddress.setLng(address.getLng());
        daoAddress.setStreet(address.getStreet());
        daoAddress.setZipcode(address.getZipcode());
        return daoAddress;
    }
}
