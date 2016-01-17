package org.jboss.as.quickstarts.beans.impl;

import org.jboss.as.quickstarts.beans.MountainBean;
import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;
import org.jboss.as.quickstarts.utils.LoggingInterceptor;
import org.jboss.ejb3.annotation.Clustered;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Stateless
@Clustered
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MountainBeanImpl implements MountainBean {

    private Logger logger = Logger.getLogger("org.jboss.as.quickstarts");

    @Inject
    MountainService mountainService;

    @Resource
    private SessionContext ctx;

    @Resource
    TimerService timerService;

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

        timerService.createTimer(2, summit);
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

    @Override
    @Asynchronous
    @ExcludeClassInterceptors
    @ExcludeDefaultInterceptors
    public Future<Boolean> createVosges() {
        Mountain vosges = new Mountain("Les Vosges");

        Summit summit = new Summit("Le Ballon de Guebwiller", 1440);
        List<Summit> summits = Arrays.asList(summit);
        vosges.setSummits(summits);
        summits.stream().forEach(s->s.setMountain(vosges));

        mountainService.createMountain(vosges);

        return new AsyncResult<>(Boolean.TRUE);
    }

    @Override
    @Asynchronous
    public Future<Boolean> deleteVosges() {
        mountainService.deleteMountain(mountainService.findMountain("Les Vosges"));

        return new AsyncResult<>(Boolean.TRUE);
    }

    @Schedule(second = "*/40", minute = "*", hour = "*", persistent = true)
    public void testMountains() {
        List<Mountain> mountains = mountainService.findAllMountains();

        mountains.forEach(System.out::println);
    }

    @Timeout
    public void showHigher(Timer timer) {

        Summit summitCreated = (Summit) timer.getInfo();

        Summit higherSummit = findHigherSummit(summitCreated.getMountain()).orElse(summitCreated);

        System.out.println("Timer created by createSummit--- higher summit="+higherSummit);
    }

}
