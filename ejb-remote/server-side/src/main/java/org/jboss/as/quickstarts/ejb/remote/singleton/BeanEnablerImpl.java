package org.jboss.as.quickstarts.ejb.remote.singleton;

import org.jboss.as.quickstarts.beans.MountainBean;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;


@Singleton(name = "BeanEnablerEJB")
@Remote(BeanEnabler.class)
public class BeanEnablerImpl implements BeanEnabler{

    @EJB
    private MountainBean mountainBean;

   // @EJB
   // private MessageBeanProducer MessageBeanProducer;

    public BeanEnablerImpl() {
    }


    public void createMountain(String name) {
        mountainBean.createMountain(name);
    }


}
