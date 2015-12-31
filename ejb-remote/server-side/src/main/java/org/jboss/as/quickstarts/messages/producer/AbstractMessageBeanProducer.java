package org.jboss.as.quickstarts.messages.producer;


import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.jms.ConnectionFactory;

public abstract class AbstractMessageBeanProducer implements MessageBeanProducer{


    // JEE7 only
    // @Resource
    // private JMSContext context;

    @Resource(mappedName = "java:/ConnectionFactory")
    protected static ConnectionFactory connectionFactory;

    @Resource
    protected SessionContext ctx;

    @Override
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }


}
