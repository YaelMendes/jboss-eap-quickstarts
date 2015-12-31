package org.jboss.as.quickstarts.messages.producer.impl;


import org.jboss.as.quickstarts.messages.producer.AbstractMessageBeanProducer;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Destination;
import javax.jms.Topic;


@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@TopicProducer
public class SimpleTopicProducerImpl extends AbstractMessageBeanProducer{

    @Resource(lookup = "java:/jms/topic/SimpleTopic")
    private Topic topic;

    @Override
    public Destination getDestination() {
        return topic;
    }

}
