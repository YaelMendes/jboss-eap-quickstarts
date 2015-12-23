package org.jboss.as.quickstarts.beans;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.List;
import java.util.Optional;

public interface MountainBean {

    public void createMountain(Mountain mountain);

    public void createMountain(String mountainName);

    public void createSummit(Summit summit);

    public Optional<Summit> findHigherSummit(Mountain mountain);

    public Optional<Summit> findHigherSummit(String mountainName);

    List<Summit> findSummits(String mountainName);
}
