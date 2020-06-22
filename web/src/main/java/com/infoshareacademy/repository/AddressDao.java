package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;
import java.util.Queue;

@Stateless
public class AddressDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Address> findById(Long id) {
        Query query = entityManager.createNamedQuery("Address.findById");
        query.setParameter("id", id);
        return Optional.ofNullable((Address) query.getResultList().get(0));
    }

    public Address save(Address address) {
        entityManager.persist(address);
        Query query = entityManager.createNamedQuery("Address.findByStreetAndZipAndCity");
        query.setParameter("city", address.getCity());
        query.setParameter("zip", address.getZipcode());
        query.setParameter("street", address.getStreet());
        Address address1 = (Address) query.getResultList().get(0);
        return address1;
    }
}
