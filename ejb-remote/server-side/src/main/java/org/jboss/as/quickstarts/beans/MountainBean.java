package org.jboss.as.quickstarts.beans;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Local
public interface MountainBean {

    void createMountain(Mountain mountain);

    void createMountain(String mountainName);

    void deleteMountain(String mountain);

    void createSummit(Summit summit);

    Optional<Summit> findHigherSummit(Mountain mountain);

    Optional<Summit> findHigherSummit(String mountainName);

    List<Summit> findSummits(String mountainName);

    Mountain findMountain(String mountainName);

    Future<Boolean> createVosges();

    Future<Boolean> deleteVosges();
}
