package org.jboss.as.quickstarts.service;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.List;
//import org.jboss.as.quickstarts.dao.WalkTrail;


public interface MountainService {

    void createMountain(Mountain mountain);

    boolean createSummit(Summit summit);

    Mountain findMountain(String mountainName);

    void deleteMountain(Mountain mountain);

    List<Mountain> findAllMountains();
}
