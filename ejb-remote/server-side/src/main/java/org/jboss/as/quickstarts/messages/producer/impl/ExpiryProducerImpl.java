package org.jboss.as.quickstarts.messages.producer.impl;

import javax.annotation.Resource;
import javax.jms.*;

// JEE7 only
/*@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/ExpiryQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "ExpiryQueue"
                )})*/
public class ExpiryProducerImpl {

    // JEE7 only
    // @Resource
      private JMSContext context;

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
