package org.jboss.as.quickstarts.ws.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;
import org.jboss.as.quickstarts.ws.MountainWS;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Comparator;

@WebService
@Stateless
@Interceptors(LoggingInterceptor.class)
public class MountainWSImpl implements MountainWS {

    @EJB
    private MountainBean mountainBean;

    @Override
    public Mountain nameExists(String mountainName) {
        Mountain mountain = mountainBean.findMountain(mountainName);

        System.out.println("retour de nameExists="+mountain);
        return mountain;
    }

    @Override
    public boolean createMountain(@WebParam(name = "mountainName") String mountainName) {
        mountainBean.createMountain(mountainName);
        return true;
    }

    @Override
    public boolean createSummit(String mountainName,
                             String summitName,
                             int summitHeight) {

        Mountain mountain = mountainBean.findMountain(mountainName);
        Summit summit = new Summit(summitName, summitHeight);
        summit.setMountain(mountain);

        mountainBean.createSummit(summit);
        return true;
    }

    @Override
    public Summit findHigherSummit(String mountainName) {
        Summit higherSummit = mountainBean.findMountain(mountainName).getSummits().stream().max(Comparator.naturalOrder()).get();
        System.out.println("higher summit == "+higherSummit);
        return higherSummit;
    }
}
