package org.jboss.as.quickstarts.service;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
//import org.jboss.as.quickstarts.dao.WalkTrail;


public interface MountainService {

    public void createMountain(Mountain mountain);

    public boolean createSummit(Summit summit);

    public Mountain findMountain(String mountainName);

  //  void createWalkTrail(WalkTrail walkTrail);
}
