package org.jboss.as.quickstarts.ws.impl;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;
import org.jboss.as.quickstarts.ws.MountainWS;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Remote(MountainWS.class)
@WebService
@Stateless
@Interceptors(LoggingInterceptor.class)
public class MountainWSImpl implements MountainWS {

    @EJB
    MountainService mountainService;

    @Override
    @WebMethod
    public Mountain nameExists(String mountainName) {
        Mountain mountain = mountainService.findMountain(mountainName);
        System.out.println("mountain="+mountain);
        return mountain;
        System.out.println("nameExists invoqué...");
        Mountain mountain = mountainService.findMountain(mountainName);
        System.out.println("retour de nameExists="+mountain);
        return mountain;
    }
}
