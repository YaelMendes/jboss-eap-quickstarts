package org.jboss.as.quickstarts.messages.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by yael4 on 29/12/2015.
 */
public abstract class MessageTracer implements MessageListener{

    public void onMessage(Message message) {
        System.out.println("------ Message Received from :" +this.getClass().getSimpleName() + ":"+ message);

        if (message != null && message instanceof TextMessage) {
            try {
                System.out.println("cast passed!  message: " + (((TextMessage) message).getText()));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}
