package org.jboss.as.quickstarts.service.impl;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
import org.jboss.as.quickstarts.service.MountainService;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;


@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class  MountainServiceImpl implements MountainService {

    @PersistenceContext(unitName = "mountainPersistenceContextXA")
    private EntityManager entityManager;

    @Override
    public void createMountain(Mountain mountain) {
            if (entityManager.find(Mountain.class, mountain.getName(), LockModeType.OPTIMISTIC) == null) {
                entityManager.persist(mountain);
        }
    }

    @Override
    public void deleteMountain(Mountain mountain) {
        if (entityManager.find(Mountain.class, mountain.getName(), LockModeType.PESSIMISTIC_WRITE) != null) {
            entityManager.remove(mountain);
        }
    }

    @Override
    public boolean createSummit(Summit summit) {
        if (entityManager.find(Summit.class, summit.getName(), LockModeType.OPTIMISTIC) == null) {
            entityManager.persist(summit);
            return true;
        }
        return false;
    }

    @Override
    public Mountain findMountain(String mountainName) {
        return entityManager.find(Mountain.class, mountainName);
    }


}
