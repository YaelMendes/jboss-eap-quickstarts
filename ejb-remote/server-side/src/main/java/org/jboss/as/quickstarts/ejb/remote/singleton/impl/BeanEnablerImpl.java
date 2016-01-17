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
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
@Startup
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
    private SessionContext sessionContext;

    public BeanEnablerImpl() {
    }

    //@RolesAllowed({"Administrator"})
    @Override
    @PermitAll
    @Lock(LockType.WRITE)
    @AccessTimeout(2000)
    public void createMountain(String name) {

        List<String> roles = Arrays.asList("Administrator", "Auditor", "Deployer", "Maintainer", "Monitor", "Operator", "SuperUser");

        roles.stream().forEach(r -> System.out.println("isUserInRole("+r+"):"+ sessionContext.isCallerInRole(r)) );

        mountainBean.createMountain(name);
    }

    //@RolesAllowed({"Administrator"})
    @Override
    @PermitAll
    @Lock(LockType.WRITE)
    @AccessTimeout(2000)
    public void createMountain(Mountain mountain) {
        mountainBean.createMountain(mountain);
    }

    @Override
    public Mountain findMountain(String s) {
        return mountainBean.findMountain(s);
    }

    @Override
    public void addSummitToMountain(Summit summit, Mountain mountain) {
        summit.setMountain(mountain);
        mountain.getSummits().add(summit);

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
    public void createVosges() {
        mountainBean.createVosges();
    }

    @Override
    @Lock(LockType.WRITE)
    @AccessTimeout(5000)
    public void sendMessageAndCreateSummit(String mountainName, String summitName,  int summitHeight) {
        Summit summit = new Summit(summitName, summitHeight);
        summit.setMountain(mountainBean.findMountain(mountainName));

        mountainBean.createSummit(summit);
        messageBeanProducer.sendOneMessage(mountainName, summitHeight);
    }

    /*
    @Override
    public void createQueue(String queueName) {
        messageBeanCreator.createQueue(queueName);
    }*/


}
