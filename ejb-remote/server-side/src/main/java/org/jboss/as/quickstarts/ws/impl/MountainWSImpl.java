package org.jboss.as.quickstarts.ws.impl;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;
import org.jboss.as.quickstarts.ws.MountainWS;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import java.util.Comparator;

@WebService
@Stateless
@Interceptors(LoggingInterceptor.class)
public class MountainWSImpl implements MountainWS {

    @Inject
    MountainService mountainService;

    @Override
    public Mountain nameExists(String mountainName) {
        Mountain mountain = mountainService.findMountain(mountainName);

        System.out.println("retour de nameExists="+mountain);
        return mountain;
    }

    @Override
    public boolean createSummit(String mountainName,
                             String summitName,
                             int summitHeight) {

        Mountain mountain = mountainService.findMountain(mountainName);
        Summit summit = new Summit(summitName, summitHeight);
        summit.setMountain(mountain);

        return mountainService.createSummit(summit);
    }

    @Override
    public Summit findHigherSummit(String mountainName) {
        Summit higherSummit = mountainService.findMountain(mountainName).getSummits().stream().max(Comparator.naturalOrder()).get();
        System.out.println("higher summit == "+higherSummit);
        return higherSummit;
    }
}
