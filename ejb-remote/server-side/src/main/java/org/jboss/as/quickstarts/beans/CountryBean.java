package org.jboss.as.quickstarts.beans;


import javax.ejb.Remote;

@Remote
public interface CountryBean {

    void createCountry(String countryName);

    default void sysout() {
        System.out.println("coucou from the bean interface");
    }

}
