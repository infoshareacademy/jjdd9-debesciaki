package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.AddressJSON;
import com.infoshareacademy.domain.entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class AddressMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(AddressMapper.class.getName());

    public AddressJSON daoToJson(Address address) {
        AddressJSON jsonAddress = new AddressJSON();
        jsonAddress.setCity(address.getCity());
        jsonAddress.setLat(address.getLat());
        jsonAddress.setLng(address.getLng());
        jsonAddress.setStreet(address.getStreet());
        jsonAddress.setZipcode(address.getZipcode());
        STDLOG.info("Success in mapping dao to json");
        return jsonAddress;
    }

    public Address jsonToDao(AddressJSON address) {
        Address daoAddress = new Address();
        daoAddress.setCity(address.getCity());
        daoAddress.setLat(address.getLat());
        daoAddress.setLng(address.getLng());
        daoAddress.setStreet(address.getStreet());
        daoAddress.setZipcode(address.getZipcode());
        STDLOG.info("Success in mapping json to dao");
        return daoAddress;
    }
}
