package com.a1.a1.repository;

import com.a1.a1.model.DestinationModel;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.lab.SD-A1");

    public DestinationRepository(){

    }

    public DestinationModel insertDestination(String destinationName) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();

            DestinationModel destinationModel = new DestinationModel();
            destinationModel.setName(destinationName);

            em.getTransaction().begin();
            em.persist(destinationModel);
            em.getTransaction().commit();
            em.close();

            return destinationModel;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }

    }

    public DestinationModel findDestination(Integer destId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();


            DestinationModel foundDestination = em.find(DestinationModel.class, destId);
            em.getTransaction().commit();
            em.close();

            return foundDestination;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public List<DestinationModel> findAllDestination() throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            List<DestinationModel> foundDestinations = em.createQuery("SELECT a FROM DestinationModel a", DestinationModel.class).getResultList();
            em.getTransaction().commit();
            em.close();

            return foundDestinations;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public DestinationModel updateDestination(Integer destId, String destinationName) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            DestinationModel foundDestination = em.find(DestinationModel.class, destId);
            foundDestination.setName(destinationName);
            em.getTransaction().commit();
            em.close();

            return foundDestination;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public DestinationModel removeDestination(Integer destId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            DestinationModel foundDestination = em.find(DestinationModel.class, destId);
            em.remove(foundDestination);
            em.getTransaction().commit();
            em.close();

            return foundDestination;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }


    public static void main(String[] args) throws Exception {
        DestinationRepository destinationRepository = new DestinationRepository();
//        DestinationModel pack = destinationRepository.insertDestination(dest.getName());
//        DestinationModel pack = destinationRepository.findDestination(9);
        DestinationModel pack = destinationRepository.removeDestination(9);
        System.out.println(pack.toString());
    }
}
