package com.a1.a1.repository;

import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.UserModel;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.lab.SD-A1");

    public UserRepository(){}

    public UserModel insertUser(UserDTO userDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            UserModel userModel = new UserModel();
            userModel.setEmail(userDTO.getEmail());
            userModel.setPassword(userDTO.getPassword());
            userModel.setAgencyByAgencyId(userDTO.getAgency());

            em.getTransaction().begin();
            em.persist(userModel);
            em.getTransaction().commit();
            em.close();

            return userModel;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }

    }

    public UserModel findUser(Integer userId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            UserModel foundUser = em.find(UserModel.class, userId);
            em.getTransaction().commit();
            em.close();

            return foundUser;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public UserModel findUserByEmailAndPassword(UserDTO user) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UserModel> cq = cb.createQuery(UserModel.class);

            Root<UserModel> userQuery = cq.from(UserModel.class);
            Predicate emailPredicate = cb.equal(userQuery.get("email"), user.getEmail());
            Predicate passwordPredicate = cb.equal(userQuery.get("password"), user.getPassword());
            cq.where(emailPredicate, passwordPredicate);

            TypedQuery<UserModel> query = em.createQuery(cq);
            return query.getSingleResult();
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public UserModel updateUser(Integer userId, UserDTO userDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();
            UserModel foundUser = em.find(UserModel.class, userId);
            foundUser.setEmail(userDTO.getEmail());
            foundUser.setPassword(userDTO.getPassword());
            foundUser.setAgencyByAgencyId(userDTO.getAgency());
            em.getTransaction().commit();
            em.close();

            return foundUser;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public UserModel removeUser(Integer userId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            UserModel foundUser = em.find(UserModel.class, userId);
            em.remove(foundUser);
            em.getTransaction().commit();
            em.close();

            return foundUser;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }


    public static void main(String[] args) throws Exception {
        UserRepository userRepository = new UserRepository();
//        UserModel pack = userRepository.insertUser(user);
//        UserModel pack = userRepository.findUser(5);
//        UserModel pack = userRepository.updateUser(5, user);
        UserModel pack = userRepository.removeUser(8);
        System.out.println(pack.toString());
    }
}
