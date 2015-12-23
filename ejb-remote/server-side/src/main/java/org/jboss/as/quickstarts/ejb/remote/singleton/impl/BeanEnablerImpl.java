package org.jboss.as.quickstarts.ejb.remote.singleton.impl;

import org.jboss.as.quickstarts.beans.MessageBeanProducer;
import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.ejb.remote.singleton.BeanEnabler;
import org.jboss.as.quickstarts.ws.MountainWS;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;
import java.util.List;


@Singleton(name = "BeanEnablerEJB")
@Remote(BeanEnabler.class)
public class BeanEnablerImpl implements BeanEnabler{

    @EJB
    private MountainBean mountainBean;

    @Inject
    private MessageBeanProducer messageBeanProducer;

   // @WebServiceRef(wsdlLocation = "http://localhost:8080/jboss-ejb-remote-server-side/MountainWSImpl?wsdl")
  //  private MountainWS mountainWSRef;

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
    public List<Summit> findSummits(String mountainName) {
        return mountainBean.findSummits(mountainName);
    }

    @Override
    public Summit findHigherSummit(String mountainName) {
        return mountainBean.findHigherSummit(mountainName).get();
    }

 /*   @Override
    public Summit findHigherSummitWS(String mountainName) {
        return mountainWSRef.findHigherSummit(mountainName);
    }*/

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
