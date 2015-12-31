package org.jboss.as.quickstarts.messages.receiver;

import org.jboss.as.quickstarts.messages.producer.MessageBeanProducer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig =
        {
                @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:/queue/SimpleQueue"),
                @ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="InVmConnectionFactory"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = MessageBeanProducer.MOUNTAIN_HEIGHT_PROPERTY+" > 3000")
        })
public class BigMountainReceiver extends MessageTracer {

}