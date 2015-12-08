package org.jboss.as.quickstarts.service.impl;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.beans.impl.MountainBeanImpl;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by EYME5300 on 07/12/15.
 */
@Ignore
@RunWith(Arquillian.class)
public class MountainBeanImplTest {

    @EJB
    MountainBean mountainBean;

    /*
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MountainBeanImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }*/

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(MountainBean.class, MountainBeanImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testFindHigherSummit() throws Exception {

        Mountain mountain = new Mountain("Himalaya");

        List<Summit> summits = new LinkedList<>();
        summits.add(new Summit("Annapurna", 8091));
        summits.add(new Summit("Kangchenjunga", 8586));
        summits.add(new Summit("Lhotse", 8516));
        summits.add(new Summit("Everest", 8848));
        summits.add(new Summit("K2", 8611));
        summits.add(new Summit("Cho Oyu", 8201));

        mountain.setSummits(summits);

        Assert.assertEquals(8848, mountainBean.findHigherSummit(mountain).get().getHeight().intValue());
    }
}
