package org.jboss.as.quickstarts.service.impl;


import org.jboss.as.quickstarts.service.CountryService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class CountryServiceImpl implements CountryService, Serializable {

    @PersistenceContext(unitName = "mountainPersistenceContextXA")
    private EntityManager entityManager;

    @Override
    public void createCountry(String countryName) {
        entityManager.persist(countryName);
    }
}
