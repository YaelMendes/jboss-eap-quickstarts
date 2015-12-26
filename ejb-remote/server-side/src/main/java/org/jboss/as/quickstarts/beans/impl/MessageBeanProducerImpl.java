package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MessageBeanProducer;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.servlet.annotation.WebServlet;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/ExpiryQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "HelloWorldMDBQueue"
                )})
//@Stateless
//@WebServlet("/ProduceSomeMessage")
//@ManagedBean
//@Named
//@Local(MessageBeanProducer.class)
public class MessageBeanProducerImpl implements MessageBeanProducer {

    /*
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;
*/

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/ExpiryQueue")
    private Queue queue;


    @Override
    public void sendOneMessage(String text) {
        context.createProducer().send(queue, text);
    }

    @Override
    public void simpleOutBean(String s) {
        System.out.println("sysout::::::::" + s);
    }
}