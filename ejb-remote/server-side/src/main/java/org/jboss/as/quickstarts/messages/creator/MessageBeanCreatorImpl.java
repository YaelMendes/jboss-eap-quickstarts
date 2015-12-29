package org.jboss.as.quickstarts.messages.creator;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by yael4 on 29/12/2015.
 */
public class MessageBeanCreatorImpl implements MessageBeanCreator {

    @Resource(mappedName = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;


    @Override
    public void createQueue(String queueName) {

        try (
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
        ) {


            //ClientSession coreSession = ((ClientSession)session).getCoreSession();

            System.out.println("session="+(session));

         //   session.createQueue(queueName);

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
