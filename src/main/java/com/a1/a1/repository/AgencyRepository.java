package com.a1.a1.repository;

import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AgencyRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.lab.SD-A1");

    public AgencyRepository(){

    }

    public AgencyModel findAgency(Integer agencyId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();
            AgencyModel foundAgency = em.find(AgencyModel.class, agencyId);
            em.getTransaction().commit();
            em.close();

            return foundAgency;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

}
