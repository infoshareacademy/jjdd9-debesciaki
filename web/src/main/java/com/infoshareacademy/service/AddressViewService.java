package com.infoshareacademy.service;

import com.infoshareacademy.domain.api.AddressJSON;
import com.infoshareacademy.domain.entity.Address;
import com.infoshareacademy.domain.view.AddressView;
import com.infoshareacademy.repository.AddressDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AddressViewService {
    @Inject
    AddressDao addressDao;

    public Address mapper(AddressView addressView) {
        Address address = new Address();
        address.setCity(addressView.getCity());
        address.setStreet(addressView.getStreet());
        address.setZipcode(addressView.getZipCode());
        address.setId(addressDao.save(address).getId());
        return address;
    }

}
