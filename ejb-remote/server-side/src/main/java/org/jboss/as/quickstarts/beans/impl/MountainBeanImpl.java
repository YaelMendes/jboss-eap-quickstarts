package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
@Local(MountainBean.class)
@Interceptors(LoggingInterceptor.class)
public class MountainBeanImpl implements MountainBean {


    @EJB
    MountainService mountainService;

    @Override
    public void createMountain(String name) {
        mountainService.createMountain(new Mountain(name));
    }

    @Override
    public void addSummit(Mountain mountain, Summit summit) {

    }
}
