package org.jboss.as.quickstarts.messages.receiver;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

@MessageDriven(activationConfig =
        {
                @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:/topic/SimpleTopic"),
                @ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="InVmConnectionFactory"),
        })
public class TopicReceiver extends MessageTracer {

}
