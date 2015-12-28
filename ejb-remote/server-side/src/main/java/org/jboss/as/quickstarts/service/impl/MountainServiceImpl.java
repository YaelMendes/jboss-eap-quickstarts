package org.jboss.as.quickstarts.service.impl;


import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;
//import org.jboss.as.quickstarts.dao.WalkTrail;
import org.jboss.as.quickstarts.service.MountainService;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
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
    public void deleteMountain(Mountain mountain) {
        if (entityManager.find(Mountain.class, mountain.getName()) != null) {
            entityManager.remove(mountain);
        }
    }

    @Override
    public boolean createSummit(Summit summit) {
        if (entityManager.find(Summit.class, summit.getName()) == null) {
            entityManager.persist(summit);
            return true;
        }
        return false;
    }

    @Override
    public Mountain findMountain(String mountainName) {
        return entityManager.find(Mountain.class, mountainName);
    }



  /*  @Override
    public void createWalkTrail(WalkTrail walkTrail) {
        if (entityManager.find(WalkTrail.class, walkTrail.getId()) == null) {
            entityManager.persist(walkTrail);
        }
    }*/

}
