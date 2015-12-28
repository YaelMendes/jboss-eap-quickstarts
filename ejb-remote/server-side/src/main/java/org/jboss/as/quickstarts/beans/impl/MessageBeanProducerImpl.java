package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MessageBeanProducer;

import javax.annotation.Resource;
import javax.jms.*;

/*@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/ExpiryQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "ExpiryQueue"
                )})*/
public class MessageBeanProducerImpl implements MessageBeanProducer {

    // @Resource
    //  private JMSContext context;

    @Resource(mappedName = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    public void sendOneMessage(String text) {

        try {
            Session session = connectionFactory.createConnection().createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            session.createProducer(queue).send(session.createTextMessage(text));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Message method call finished....");
        }
    }

}
