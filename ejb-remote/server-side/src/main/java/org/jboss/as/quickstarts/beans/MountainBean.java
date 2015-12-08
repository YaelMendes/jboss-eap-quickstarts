package org.jboss.as.quickstarts.beans;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.Optional;

public interface MountainBean {


    public void createMountain(String name);

    public void addSummit(Mountain mountain, Summit summit);

    public Optional<Summit> findHigherSummit(Mountain mountain);

}
