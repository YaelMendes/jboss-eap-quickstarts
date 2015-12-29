package org.jboss.as.quickstarts.messages.receiver;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;


@MessageDriven(mappedName="jms/queue/ExpiryQueue", activationConfig =
        {
                @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
                @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="java:/queue/ExpiryQueue"),
                @ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="InVmConnectionFactory"),
                @ActivationConfigProperty(propertyName="MaxPoolSize", propertyValue="1"),
                @ActivationConfigProperty(propertyName="MaxMessages", propertyValue="1"),
                @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),

        })
public class ExpiryReceiver extends MessageTracer {

}
