package org.jboss.as.quickstarts.messages.producer.impl;

import org.jboss.as.quickstarts.messages.producer.AbstractMessageBeanProducer;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.jms.Destination;
import javax.jms.Queue;

// JEE7 only
/*@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/SimpleQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "SimpleQueue"
                )})*/
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@QueueProducer
@Default
public class SimpleQueueProducerImpl extends AbstractMessageBeanProducer {

    @Resource(lookup = "java:/jms/queue/SimpleQueue")
    private Queue queue;


    @Override
    public void sendOneMessage(String mountainName, int mountainHeight) {
        super.sendOneMessage(mountainName, mountainHeight);
        if (mountainHeight <= 0) {
            System.out.println("ctx.setRollbackOnly() called !");
            ctx.setRollbackOnly();
        }
    }

    @Override
    public Destination getDestination() {
        return queue;
    }

}
