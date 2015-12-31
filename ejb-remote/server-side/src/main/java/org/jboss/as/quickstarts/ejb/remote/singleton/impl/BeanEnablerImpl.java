package org.jboss.as.quickstarts.ejb.remote.singleton.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.ejb.remote.singleton.BeanEnabler;
import org.jboss.as.quickstarts.messages.creator.MessageBeanCreator;
import org.jboss.as.quickstarts.messages.producer.MessageBeanProducer;
import org.jboss.as.quickstarts.messages.producer.impl.TopicProducer;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;


@Singleton(name = "BeanEnablerEJB")
@Remote(BeanEnabler.class)
@PermitAll
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BeanEnablerImpl implements BeanEnabler {

    @EJB
    private MountainBean mountainBean;

    @Inject
    private MessageBeanProducer messageBeanProducer;

    @Inject
    @TopicProducer
    private MessageBeanProducer messageBeanProducerTopic;

    @Inject
    private MessageBeanCreator messageBeanCreator;

   // @WebServiceRef(wsdlLocation = "http://localhost:8080/jboss-ejb-remote-server-side/MountainWSImpl?wsdl")
  //  private MountainWS mountainWSRef;

    @Resource
    private SessionContext ctx;

    public BeanEnablerImpl() {
    }

    @Override
    @PermitAll
    //@RolesAllowed({"Administrator"})
    public void createMountain(String name) {

        System.out.println("principal in BeanEnablerImpl.createMountain:"+ctx.getCallerPrincipal());
        System.out.println("principal.name in BeanEnablerImpl.createMountain:"+ctx.getCallerPrincipal().getName());

        List<String> roles = Arrays.asList("Administrator", "Auditor", "Deployer", "Maintainer", "Monitor", "Operator", "SuperUser");

        roles.stream().forEach(r -> System.out.println("isUserInRole("+r+"):"+ctx.isCallerInRole(r)) );

        mountainBean.createMountain(name);
    }

    @Override
    @PermitAll
    //@RolesAllowed({"Administrator"})
    public void createMountain(Mountain mountain) {
        mountainBean.createMountain(mountain);
    }

    @Override
    @PermitAll
    public List<Summit> findSummits(String mountainName) {
        return mountainBean.findSummits(mountainName);
    }

    @Override
    public Summit findHigherSummit(String mountainName) {
        return mountainBean.findHigherSummit(mountainName).get();
    }

    @Override
    public void sendOneMessage(String mountainName, int mountainHeight) {
        messageBeanProducer.sendOneMessage(mountainName, mountainHeight);
    }

    @Override
    public void sendTopicMessage(String mountainName, int mountainHeight) {
        messageBeanProducerTopic.sendOneMessage(mountainName, mountainHeight);
    }

    @Override
    public void sendMessageAndCreateSummit(String mountainName, String summitName,  int summitHeight) {
        Summit summit = new Summit();
        summit.setMountain(mountainBean.findMountain(mountainName));
        summit.setName(summitName);
        summit.setHeight(summitHeight);
        mountainBean.createSummit(summit);
        messageBeanProducer.sendOneMessage(mountainName, summitHeight);
    }

    /*
    @Override
    public void createQueue(String queueName) {
        messageBeanCreator.createQueue(queueName);
    }*/


}
