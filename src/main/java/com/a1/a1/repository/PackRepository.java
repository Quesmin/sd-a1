package com.a1.a1.repository;

import com.a1.a1.dto.PackDTO;
import com.a1.a1.dto.PackFilterDTO;
import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;
import com.a1.a1.model.PackModel;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class PackRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.lab.SD-A1");

    public PackRepository() {
    }

    public PackModel insertPack(PackDTO packDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            PackModel packModel = new PackModel();
            packModel.setName(packDTO.getName());
            packModel.setMaxSlots(packDTO.getMaxSlots());
            packModel.setExtraDetails(packDTO.getExtraDetails());
            packModel.setPrice(packDTO.getPrice());
            packModel.setStartDate(packDTO.getStartDate());
            packModel.setEndDate(packDTO.getEndDate());
            packModel.setMaxSlots(packDTO.getMaxSlots());
            packModel.setDestinationByDestinationId(packDTO.getDestination());
            packModel.setAgencyByAgencyId(packDTO.getAgency());

            em.getTransaction().begin();
            em.persist(packModel);
            em.getTransaction().commit();
            em.close();

            return packModel;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }

    }

    public PackModel findPack(Integer packId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            PackModel foundPack = em.find(PackModel.class, packId);
            em.getTransaction().commit();
            em.close();

            return foundPack;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public List<PackModel> findAllPack() throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            List<PackModel> foundPack = em.createQuery("SELECT a FROM PackModel a", PackModel.class).getResultList();
            em.getTransaction().commit();
            em.close();

            return foundPack;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public List<PackModel> findPackagesByAgency(AgencyModel agency) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<PackModel> cq = cb.createQuery(PackModel.class);

            Root<PackModel> packQuery = cq.from(PackModel.class);
            Predicate agencyPredicate = cb.equal(packQuery.get("agencyByAgencyId"), agency);
            cq.where(agencyPredicate);

            TypedQuery<PackModel> query = em.createQuery(cq);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }


    public List<PackModel> filterPackages(PackFilterDTO filterDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<PackModel> cq = cb.createQuery(PackModel.class);

            Root<PackModel> packQuery = cq.from(PackModel.class);
            Predicate predicate = cb.conjunction();

            DestinationModel destination = filterDTO.getDestination();
            Integer minPrice = filterDTO.getMinPrice();
            Integer maxPrice = filterDTO.getMaxPrice();
            Date startDate = filterDTO.getStartDate();
            Date endDate = filterDTO.getEndDate();

            if (destination != null) {
                predicate = cb.and(predicate, cb.equal(packQuery.get("destinationByDestinationId"), destination));
            }
            if (minPrice != null) {
                predicate = cb.and(predicate, cb.ge(packQuery.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.le(packQuery.get("price"), maxPrice));
            }
            if (startDate != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(packQuery.get("startDate"), startDate));
            }
            if (endDate != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(packQuery.get("endDate"), endDate));
            }
            cq.where(predicate);
            return em.createQuery(cq).getResultList();
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public PackModel updatePack(Integer packId, PackDTO packDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            PackModel foundPack = em.find(PackModel.class, packId);
            foundPack.setName(packDTO.getName());
            foundPack.setMaxSlots(packDTO.getMaxSlots());
            foundPack.setExtraDetails(packDTO.getExtraDetails());
            foundPack.setPrice(packDTO.getPrice());
            foundPack.setStartDate(packDTO.getStartDate());
            foundPack.setEndDate(packDTO.getEndDate());
            foundPack.setMaxSlots(packDTO.getMaxSlots());
            foundPack.setDestinationByDestinationId(packDTO.getDestination());
            em.getTransaction().commit();
            em.close();

            return foundPack;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public PackModel removePack(Integer packId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            PackModel foundPack = em.find(PackModel.class, packId);
            em.remove(foundPack);
            em.getTransaction().commit();
            em.close();

            return foundPack;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }


    public static void main(String[] args) throws Exception {
        PackRepository packRepository = new PackRepository();

//        PackModel pack = packRepository.insertPack(packDTO);
//        PackModel pack = packRepository.findPack(8);
//        PackModel pack = packRepository.updatePack(8, packDTO);
        PackModel pack = packRepository.removePack(9);
        System.out.println(pack.toString());
    }
}
