package org.jboss.as.quickstarts.service.impl;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class  MountainServiceImpl implements MountainService {

    //@PersistenceContext(unitName = "mountainPersistenceContext")
    //private EntityManager entityManager;

    public void createMountain(Mountain mountain) {

        System.out.println("Entering Service.createMountain...");

        //TODO
    }

    public void createSummit(Summit summit) {
        //TODO
    }
}
