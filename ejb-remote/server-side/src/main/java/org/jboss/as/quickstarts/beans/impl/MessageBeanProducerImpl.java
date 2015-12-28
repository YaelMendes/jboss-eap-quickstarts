package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MessageBeanProducer;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.servlet.annotation.WebServlet;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/jms/queue/ExpiryQueue",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "HelloWorldMDBQueue"
                )})
//@Model
public class MessageBeanProducerImpl implements MessageBeanProducer {

  //  @Inject
  //  private JMSContext context;

    @Resource(lookup = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    public void sendOneMessage(String text) {
/*
        QueueConnection cnn = null;
        QueueSender sender = null;
        QueueSession session = null;
        InitialContext ctx = new InitialContext();
        Queue queue = (Queue) ctx.lookup("queue/tutorial/example");
        QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
        cnn = factory.createQueueConnection();
        session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        TextMessage msg = session.createTextMessage("Hello World");

        sender = session.createSender(queue);
        sender.send(msg);*/
        System.out.println("Message sent successfully to remote queue.");
    }

    public void simpleOutBean(String s) {
        System.out.println("DEPUIS MessageProd:"+s);
    }


}
