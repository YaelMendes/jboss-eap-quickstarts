package org.jboss.as.quickstarts.messages.producer.impl;

import org.jboss.as.quickstarts.messages.producer.MessageBeanProducer;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.*;

// JEE7 only
/*@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/SimpleQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "SimpleQueue"
                )})*/
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class SimpleProducerImpl implements MessageBeanProducer {

    // JEE7 only
    // @Resource
    // private JMSContext context;

    @Resource(mappedName = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/SimpleQueue")
    private Queue queue;

    @Resource
    private SessionContext ctx;

    @Override
    public void sendOneMessage(String mountainName, int mountainHeight) {
        MessageBeanProducer.super.sendOneMessage(mountainName, mountainHeight);
        if (mountainHeight <= 0) {
            System.out.println("ctx.setRollbackOnly() called !");
            ctx.setRollbackOnly();
        }
    }

    @Override
    public Destination getQueue() {
        return queue;
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
