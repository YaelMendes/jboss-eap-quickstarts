package org.jboss.as.quickstarts.beans;

import org.jboss.as.quickstarts.utils.LoggingInterceptor;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(mappedName="jms/queue/ExpiryQueue", activationConfig =
        {
                @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:/queue/ExpiryQueue"),
                @ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="ConnectionFactory"),
                @ActivationConfigProperty(propertyName="MaxPoolSize", propertyValue="1"),
                @ActivationConfigProperty(propertyName="MaxMessages", propertyValue="1"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),

        })
public class MessageBeanReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("------ Message Received:"+message);
    }
}
