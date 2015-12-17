package org.jboss.as.quickstarts.service.impl;

import org.jboss.as.quickstarts.utils.SimpleComparator;
import org.junit.Assert;

/**
 * Created by EYME5300 on 08/12/15.
 */
public class GenericTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testFindHigherSummit() throws Exception {


        org.junit.Assert.assertEquals(0, SimpleComparator.comparing(4, 2+2));
    }

}
