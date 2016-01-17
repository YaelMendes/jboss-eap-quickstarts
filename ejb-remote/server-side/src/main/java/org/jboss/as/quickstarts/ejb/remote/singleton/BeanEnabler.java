package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BeanEnabler {

    void createMountain(String mountainName);

    void createMountain(Mountain mountain);

    Mountain findMountain(String s);

    void addSummitToMountain(Summit summit, Mountain mountain);

    List<Summit> findSummits(String mountainName);

    Summit findHigherSummit(String mountainName);

    void sendOneMessage(String mountainName, int mountainHeight);

    void sendMessageAndCreateSummit(String mountainName, String summitName, int summitHeight);

    void sendTopicMessage(String mountainName, int mountainHeight);

    void createVosges();

}
