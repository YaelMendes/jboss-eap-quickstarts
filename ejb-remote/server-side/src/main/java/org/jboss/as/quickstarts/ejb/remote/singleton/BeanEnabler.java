package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import java.util.List;


public interface BeanEnabler {

    void createMountain(String mountainName);

    void createMountain(Mountain mountain);

    List<Summit> findSummits(String mountainName);

    Summit findHigherSummit(String mountainName);

    void sendOneMessage(String mountainName, int mountainHeight);

    void sendMessageAndCreateSummit(String mountainName, String summitName, int summitHeight);

    //  void createQueue(String newQueue1);
}
