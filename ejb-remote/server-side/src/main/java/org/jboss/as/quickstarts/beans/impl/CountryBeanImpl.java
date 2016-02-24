package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.CountryBean;
import org.jboss.as.quickstarts.dao.Country;
import org.jboss.as.quickstarts.service.CountryService;
import org.jboss.ejb3.annotation.CacheConfig;
import org.jboss.ejb3.cache.Optimized;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;

@Stateful(name = "CountryBeanEJB")
@StatefulTimeout(30)
@CacheConfig(maxSize=5000,removalTimeoutSeconds=18000)
public class CountryBeanImpl implements CountryBean, Optimized {

    @Inject
    CountryService countryService;

    private Country innerCountry;

    @Override
    public void createCountry(String countryName) {
        countryService.createCountry(countryName);
    }

    public Country modifyCountry(Country country) {
        // come code ...
        return new Country();
    }

    @Override
    public boolean isModified() {
        return ! innerCountry.getName().equalsIgnoreCase(modifyCountry(innerCountry).getName());
    }
}
