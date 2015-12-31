package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Stateless
@Local(MountainBean.class)
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MountainBeanImpl implements MountainBean {

    @Inject
    MountainService mountainService;

    @Resource
    private SessionContext ctx;

    @Override
    public void createMountain(Mountain mountain) {
        mountainService.createMountain(mountain);
    }

    @Override
    public void createMountain(String name) {
        System.out.println("principal in MountainBean.createMountain:"+ctx.getCallerPrincipal());
        System.out.println("principal.name in MountainBean.createMountain:"+ctx.getCallerPrincipal().getName());

        mountainService.createMountain(new Mountain(name));
    }

    @Override
    public void deleteMountain(Mountain mountain) {
        mountainService.deleteMountain(mountain);
    }

    @Override
    public void createSummit(Summit summit) {
        mountainService.createSummit(summit);
    }

    @Override
    public Optional<Summit> findHigherSummit(Mountain mountain) {
        return  mountain.getSummits().stream().max(Comparator.naturalOrder());
    }

    @Override
    public Optional<Summit> findHigherSummit(String mountainName) {
        return findHigherSummit(mountainService.findMountain(mountainName));
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

    @Override
    public Mountain findMountain(String mountainName) {
        return  mountainService.findMountain(mountainName);
    }
}
