package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.List;

/**
 * Created by EYME5300 on 30/11/15.
 */
public interface BeanEnabler {

    public void createMountain(String mountainName);

    public void createMountain(Mountain mountain);

    public List<Summit> findSummits(String mountainName);

    public Summit findHigherSummit(String mountainName);

    public void sendOneMessage(String s);

    public void simpleOut(String s);
}
