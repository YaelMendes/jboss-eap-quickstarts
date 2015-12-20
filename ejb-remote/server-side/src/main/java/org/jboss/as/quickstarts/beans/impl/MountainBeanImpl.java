package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.interceptor.Interceptors;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Stateless
@Local(MountainBean.class)
@Interceptors(LoggingInterceptor.class)
public class MountainBeanImpl implements MountainBean {


    @EJB
    MountainService mountainService;

    @Override
    public void createMountain(Mountain mountain) {
        mountainService.createMountain(mountain);
    }

    @Override
    public void createMountain(String name) {
        mountainService.createMountain(new Mountain(name));
    }

    @Override
    public void createSummit(Summit summit) {
        mountainService.createSummit(summit);
    }

    @Override
    public Optional<Summit> findHigherSummit(Mountain mountain) {
        return  mountain.getSummits().stream().max(Comparator.naturalOrder());
    }
/*
    @Override
    public Optional<Summit> findHigherSummit(String mountainName) {
        return findHigherSummit(mountainService.findMountain(mountainName));
    }*/

    @Override
    public List<Summit> findSummits(String mountainName) {
        return mountainService.findMountain(mountainName).getSummits();
    }
}
