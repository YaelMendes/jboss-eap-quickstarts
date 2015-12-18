package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.beans.MessageBeanProducer;
import org.jboss.as.quickstarts.dao.Mountain;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.inject.Inject;


@Singleton(name = "BeanEnablerEJB")
@Remote(BeanEnabler.class)
public class BeanEnablerImpl implements BeanEnabler{

    @EJB
    private MountainBean mountainBean;

    @Inject
    private MessageBeanProducer messageBeanProducer;

    public BeanEnablerImpl() {
    }

    @Override
    public void createMountain(String name) {
        mountainBean.createMountain(name);
    }

    @Override
    public void createMountain(Mountain mountain) {
        mountainBean.createMountain(mountain);
    }

    @Override
    public void sendOneMessage(String text) {
        messageBeanProducer.sendOneMessage(text);
    }

    @Override
    public void simpleOut(String s) {
        //TODO : call to simple out from lbeanProducer
        messageBeanProducer.simpleOutBean(s);
    }


}
