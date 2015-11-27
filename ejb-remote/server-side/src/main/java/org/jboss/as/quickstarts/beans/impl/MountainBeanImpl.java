package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(MountainBean.class)
public class MountainBeanImpl implements MountainBean {




    @Override
    public void createMountain(String name) {

    }

    @Override
    public void addSummit(Mountain mountain, Summit summit) {

    }
}
