package org.jboss.as.quickstarts.service;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;



public interface MountainService {

    public void createMountain(Mountain mountain);

    public void createSummit(Summit summit);

}
