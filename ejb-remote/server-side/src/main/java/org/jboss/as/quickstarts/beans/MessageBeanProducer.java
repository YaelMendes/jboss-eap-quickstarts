package org.jboss.as.quickstarts.beans;

/**
 * Created by EYME5300 on 10/12/15.
 */
public interface MessageBeanProducer {

    void sendOneMessage(String text);

    void simpleOutBean(String s);
}
