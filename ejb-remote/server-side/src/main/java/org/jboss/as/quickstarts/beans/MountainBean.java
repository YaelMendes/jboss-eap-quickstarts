package org.jboss.as.quickstarts.beans;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.List;
import java.util.Optional;

public interface MountainBean {

    void createMountain(Mountain mountain);

    void createMountain(String mountainName);

    void deleteMountain(Mountain mountain);

    void createSummit(Summit summit);

    Optional<Summit> findHigherSummit(Mountain mountain);

    Optional<Summit> findHigherSummit(String mountainName);

    List<Summit> findSummits(String mountainName);

    Mountain findMountain(String mountainName);
}
