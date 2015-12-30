package org.jboss.as.quickstarts.messages.producer;

import javax.jms.*;

public interface MessageBeanProducer {

    String MOUNTAIN_HEIGHT_PROPERTY = "mountainHeight";

    ConnectionFactory getConnectionFactory();

    Destination getQueue();

    default void sendOneMessage(String mountainName, int mountainHeight) {
        try(
                Connection connection = getConnectionFactory().createConnection();
                Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(getQueue())
        ) {

            TextMessage textMessage = session.createTextMessage(mountainName);
            textMessage.setIntProperty(MOUNTAIN_HEIGHT_PROPERTY, mountainHeight);
            producer.send(textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Message method call finished....");
        }
    }


}
