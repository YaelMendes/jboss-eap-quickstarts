package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.dao.Mountain;

import java.rmi.RemoteException;

/**
 * Created by EYME5300 on 30/11/15.
 */
public interface BeanEnabler {

    public void createMountain(String name);

    public void createMountain(Mountain mountain);

    public void sendOneMessage(String s);

    public void simpleOut(String s);
}
