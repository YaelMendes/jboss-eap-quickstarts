package org.jboss.as.quickstarts.messages.producer;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public interface MessageBeanProducer {

    String MOUNTAIN_HEIGHT_PROPERTY = "mountainHeight";

    ConnectionFactory getConnectionFactory();

    Destination getDestination();

    default void sendOneMessage(String mountainName, int mountainHeight) {
        try(
                Connection connection = getConnectionFactory().createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(getDestination())
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
