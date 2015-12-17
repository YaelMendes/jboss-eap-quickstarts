package org.jboss.as.quickstarts.ws.impl;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.ws.MountainWS;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Remote(MountainWS.class)
@WebService
@Stateless
public class MountainWSImpl implements MountainWS {

    @EJB
    MountainService mountainService;

    @Override
    @WebMethod
    public Mountain nameExists(String mountainName) {
        return mountainService.findMountain(mountainName);
    }
}
