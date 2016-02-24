/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejb.remote.client;

import org.jboss.as.quickstarts.beans.CountryBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.ejb.remote.singleton.BeanEnabler;
import org.jboss.as.quickstarts.ejb.remote.stateful.RemoteCounter;
import org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * A sample program which acts a remote client for a EJB deployed on JBoss EAP server. This program shows how to lookup stateful and
 * stateless beans via JNDI and then invoke on them
 *
 * @author Jaikiran Pai
 */
public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {
        // Invoke a stateless bean
        //invokeStatelessBean();

        // Invoke a stateful bean
        //invokeStatefulBean();

        // Create a mountain
        final BeanEnabler beanEnabler = lookupRemoteSingletonBeanEnabler();
        System.out.println("Calling bean.createMountain...");

        createSomeMountains(beanEnabler);

        createJura(beanEnabler);

        testJMSsendMsg(beanEnabler);

        testJMSTopicsendMsg(beanEnabler);

        createVosgesWS(beanEnabler);

        createLeReculet(beanEnabler);

        createMassifArmoricain(beanEnabler);

        deleteMassifArmoricain(beanEnabler);

       // createCountryFrance();

        invokeStatelessBean();

        invokeStatefulBean();

        System.out.println("...main() finish");
    }

    private static void createCountryFrance() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        final CountryBean countryBean = (CountryBean) context.lookup("ejb:/jboss-ejb-remote-server-side/CountryBeanEJB!"
                + CountryBean.class.getName() + "?stateful");

        //countryBean.createCountry("La France");

        countryBean.sysout();
    }

    private static void deleteMassifArmoricain(BeanEnabler beanEnabler) {
        beanEnabler.deleteMountain("Massif Armoricain");
    }

    private static void createMassifArmoricain(BeanEnabler beanEnabler) {
        Mountain armoricain = new Mountain("Massif Armoricain");
        beanEnabler.createMountain(armoricain);
    }

    private static void createLeReculet(BeanEnabler beanEnabler) {
        Summit summit = new Summit("Le Reculet", 1719);

        beanEnabler.addSummitToMountain(summit, beanEnabler.findMountain("Le Jura"));
    }

    private static void createVosgesWS(BeanEnabler beanEnabler) {
        beanEnabler.createVosges();
    }

    private static void testJMSTopicsendMsg(BeanEnabler beanEnabler) {
        beanEnabler.sendTopicMessage("Pic d 'Anie O Topic", 9999);
    }

    /*
    private static void testCreateQueue(BeanEnabler beanEnabler) {

        beanEnabler.createQueue("newQueue1");
    }*/

    private static void testJMSsendMsg(BeanEnabler beanEnabler) {

        beanEnabler.sendOneMessage("Pic d 'Anie", 2547);
        beanEnabler.sendOneMessage("Dent d'orlu", 2222);
        beanEnabler.sendOneMessage("Pic d'aneto", 3404);
        beanEnabler.sendOneMessage("L'Ossau", 2889);
        beanEnabler.sendOneMessage("Mont Blanc", 4810);

        beanEnabler.sendOneMessage("in see deeper summit", -1547);

        beanEnabler.sendMessageAndCreateSummit("Les Pyrénées", "La Rhune", 1100);

        beanEnabler.sendMessageAndCreateSummit("Les Pyrénées", "Mont ren du tout que dalle", -7847);

        beanEnabler.sendMessageAndCreateSummit("Les Pyrénées", "La dent d'Orlu", 2222);
    }

    private static void createJura(BeanEnabler beanEnabler) {
        Mountain jura = new Mountain("Le Jura");

        Summit summit = new Summit("crêt de la neige", 1720);
        List<Summit> summits = Arrays.asList(summit);
        jura.setSummits(summits);
        summits.stream().forEach(s->s.setMountain(jura));
        beanEnabler.createMountain(jura);
    }

    private static void createSomeMountains(BeanEnabler beanEnabler) {
        beanEnabler.createMountain("Les Pyrénées");
        beanEnabler.createMountain("Les Alpes");
        beanEnabler.createMountain("La Cordillère des Andes");
        beanEnabler.createMountain("L'Himalaya");
    }

    private static void testCollection() {

        Collection collection = new ArrayList();

        //collection.

        List list = new ArrayList<>();

        HashMap hashMap = new HashMap();
        hashMap.put(null, null);

        // NPE !!!
        Hashtable hashtable = new Hashtable<>();
        hashtable.put(null, null);

        HashSet hashSet = new HashSet();
        hashSet.add(null);

    }

    private static void producesSomeMessage() {
        try {
            /*
            final Hashtable<String, String> jndiProperties = new Hashtable<>();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            final Context context = new InitialContext(jndiProperties);

            Destination destination = (Destination) context.lookup("java:/jms/queue/ExpiryQueue");

            */

            // Récupération du contexte JNDI
            Context jndiContext = new InitialContext();

            // Recherche des objets administrés
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/javaee6/ConnectionFactory");
            Queue queue = (Queue) jndiContext.lookup("jms/javaee6/Queue");
// Création des artéfacts nécessaires pour se connecter
// à la file
            javax.jms.Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
// Envoi d’un message de texte à la file
            TextMessage message = session.createTextMessage();
            message.setText("This is a text message");
            producer.send(message);
            connection.close();


            // java:/ConnectionFactory
            // java:jboss/exported/jms/RemoteConnectionFactory
          /*  QueueConnectionFactory queueConnectionFactory =
                    (QueueConnectionFactory) context.lookup("java:/JmsXA");

            QueueConnection connection = queueConnectionFactory.createQueueConnection();

            Session session = connection.createSession();

            MessageProducer producer = session.createProducer(destination);

            Message message = session.createTextMessage("coucou la cou");
            producer.send(destination, message);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }*/


        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static BeanEnabler lookupRemoteSingletonBeanEnabler() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
      //  jndiProperties.put("jboss.naming.client.ejb.context", "true");
        final Context context = new InitialContext(jndiProperties);

        // The JNDI lookup name for a stateless session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : EAP allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.

        // let's do the lookup

       // showJndi();

        BeanEnabler beanEnabler = (BeanEnabler) context.lookup("ejb:/jboss-ejb-remote-server-side/BeanEnablerEJB!"
                + BeanEnabler.class.getName());

        //beanEnabler = (BeanEnabler) context.lookup("java:app/jboss-ejb-remote-server-side/BeanEnablerEJB");

        System.out.println("beanEnabler="+beanEnabler);

        return beanEnabler;
    }

    /**
     * Looks up a stateless bean and invokes on it
     *
     * @throws NamingException
     */
    private static void invokeStatelessBean() throws NamingException {
        // Let's lookup the remote stateless calculator
        final RemoteCalculator statelessRemoteCalculator = lookupRemoteStatelessCalculator();
        System.out.println("Obtained a remote stateless calculator for invocation:"+statelessRemoteCalculator);
        // invoke on the remote calculator
        int a = 10;
        int b = 20;
        System.out.println("Adding " + a + " and " + b + " via the remote stateless calculator deployed on the server");
        int sum = statelessRemoteCalculator.add(a, b);
        System.out.println("Remote calculator returned sum = " + sum);
        if (sum != a + b) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect sum " + sum + " ,expected sum was "
                + (a + b));
        }
        // try one more invocation, this time for subtraction
        int num1 = 500;
        int num2 = 400;
        System.out.println("Subtracting " + num2 + " from " + num1
            + " via the remote stateless calculator deployed on the server");
        int difference = statelessRemoteCalculator.subtract(num1, num2);
        System.out.println("Remote calculator returned difference = " + difference);
        if (difference != num1 - num2) {
            throw new RuntimeException("Remote stateless calculator returned an incorrect difference " + difference
                + " ,expected difference was " + (num1 - num2));
        }
    }

    /**
     * Looks up a stateful bean and invokes on it
     *
     * @throws NamingException
     */
    private static void invokeStatefulBean() throws NamingException {
        // Let's lookup the remote stateful counter
        final RemoteCounter statefulRemoteCounter = lookupRemoteStatefulCounter();

        // possible with injection for a remote client?


        System.out.println("Obtained a remote stateful counter for invocation");
        // invoke on the remote counter bean
        final int NUM_TIMES = 5;
        System.out.println("Counter will now be incremented " + NUM_TIMES + " times");
        for (int i = 0; i < NUM_TIMES; i++) {
            System.out.println("Incrementing counter");
            statefulRemoteCounter.increment();
            System.out.println("Count after increment is " + statefulRemoteCounter.getCount());
        }
        // now decrementing
        System.out.println("Counter will now be decremented " + NUM_TIMES + " times");
        for (int i = NUM_TIMES; i > 0; i--) {
            System.out.println("Decrementing counter");
            statefulRemoteCounter.decrement();
            System.out.println("Count after decrement is " + statefulRemoteCounter.getCount());
        }
    }

    /**
     * Looks up and returns the proxy to remote stateless calculator bean
     *
     * @return
     * @throws NamingException
     */
    private static RemoteCalculator lookupRemoteStatelessCalculator() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        // The JNDI lookup name for a stateless session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : EAP allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.

        // let's do the lookup

       // showJndi();

        return (RemoteCalculator) context.lookup("ejb:/jboss-ejb-remote-server-side/RemoteCalculatorImpl!"
            + RemoteCalculator.class.getName());
    }

    private static void showJndi() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
        env.put(Context.SECURITY_PRINCIPAL, "admin");
        env.put(Context.SECURITY_CREDENTIALS, "israel2016!");

        listNamingEnumeration(env, "java:jboss");
        listNamingEnumeration(env, "java:global");
        listNamingEnumeration(env, "java:com");
        listNamingEnumeration(env, "java:app");

        listNamingEnumeration(env, "ejb:");
        listNamingEnumeration(env, "ejb:/jboss-ejb-remote-server-side");
        listNamingEnumeration(env, "ejb:/jboss-ejb-remote-server-side/CalculatorBean!");
    }

    private static void listNamingEnumeration(Hashtable env, String prefix) {
        try {
            Context ctx = new InitialContext(env);

            NamingEnumeration<NameClassPair> namingEnumeration = ctx.list(prefix);

            System.out.println("namingEnumeration="+namingEnumeration);

            while (namingEnumeration.hasMore()) {
                System.out.println(namingEnumeration.next().getName());
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Looks up and returns the proxy to remote stateful counter bean
     *
     * @return
     * @throws NamingException
     */
    private static RemoteCounter lookupRemoteStatefulCounter() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        // The JNDI lookup name for a stateful session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>?stateful
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : EAP allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.

        // let's do the lookup
        return (RemoteCounter) context.lookup("ejb:/jboss-ejb-remote-server-side/RemoteCounterImpl!"
            + RemoteCounter.class.getName() + "?stateful");
    }
}
