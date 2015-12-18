package org.jboss.as.quickstarts.service.impl;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Singleton
public class  MountainServiceImpl implements MountainService {

    @PersistenceContext(unitName = "mountainPersistenceContextXA")
    private EntityManager entityManager;

    @Override
    public void createMountain(Mountain mountain) {
            if (entityManager.find(Mountain.class, mountain.getName()) == null) {
                entityManager.persist(mountain);
        }
    }

    @Override
    public void createSummit(Summit summit) {
        if (entityManager.find(Summit.class, summit.getName()) == null) {
            entityManager.persist(summit);
        }
    }

    @Override
    public Mountain findMountain(String mountainName) {
        return entityManager.find(Mountain.class, mountainName);
    }
}
