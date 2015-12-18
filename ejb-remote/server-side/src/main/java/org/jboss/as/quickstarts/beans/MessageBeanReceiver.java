package org.jboss.as.quickstarts.beans;

import org.jboss.as.quickstarts.utils.LoggingInterceptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(name = "MessageReceiverEJB", activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName="destination", propertyValue="jms/javaee6/ExpiryQueue"),
}
)
//@Interceptors(LoggingInterceptor.class)
public class MessageBeanReceiver implements MessageListener {
    public MessageBeanReceiver() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("------ Message Received:"+message);
    }
}
