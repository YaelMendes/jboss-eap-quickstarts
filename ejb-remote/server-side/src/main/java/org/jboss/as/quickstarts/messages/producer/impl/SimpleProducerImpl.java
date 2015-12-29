package org.jboss.as.quickstarts.messages.producer.impl;

import org.jboss.as.quickstarts.messages.producer.MessageBeanProducer;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by yael4 on 29/12/2015.
 */
public class SimpleProducerImpl implements MessageBeanProducer {

    @Resource(mappedName = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/SimpleQueue")
    private Queue queue;


    @Override
    public Destination getQueue() {
        return queue;
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
