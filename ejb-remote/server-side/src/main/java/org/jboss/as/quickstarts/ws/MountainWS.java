package org.jboss.as.quickstarts.ws;

import org.jboss.as.quickstarts.dao.Mountain;

import javax.jws.WebService;

@WebService
public interface MountainWS {

    public Mountain nameExists(String mountainName);

}
