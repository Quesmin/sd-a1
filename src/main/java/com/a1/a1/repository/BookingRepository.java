package com.a1.a1.repository;

import com.a1.a1.dto.BookingDTO;
import com.a1.a1.model.BookingModel;
import com.a1.a1.model.PackModel;
import com.a1.a1.model.UserModel;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookingRepository {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.lab.SD-A1");

    public BookingRepository(){
    }

    public BookingModel insertBooking(BookingDTO bookingDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();

            BookingModel bookingModel = new BookingModel();
            bookingModel.setPackByPackId(bookingDTO.getPack());
            bookingModel.setUserByUserId(bookingDTO.getUser());

            em.getTransaction().begin();
            em.persist(bookingModel);
            em.getTransaction().commit();
            em.close();

            return bookingModel;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }

    }

    public List<BookingModel> findAllBookings() throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            List<BookingModel> foundBookings = em.createQuery("SELECT a FROM BookingModel a", BookingModel.class).getResultList();
            em.getTransaction().commit();
            em.close();

            return foundBookings;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public BookingModel findBooking(Integer bookingId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            BookingModel foundBooking = em.find(BookingModel.class, bookingId);
            em.getTransaction().commit();
            em.close();

            return foundBooking;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public BookingModel updateBooking(Integer bookingId, BookingDTO bookingDTO) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();
            BookingModel foundBooking = em.find(BookingModel.class, bookingId);
            foundBooking.setUserByUserId(bookingDTO.getUser());
            foundBooking.setPackByPackId(bookingDTO.getPack());
            em.getTransaction().commit();
            em.close();

            return foundBooking;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }

    public BookingModel removeBooking(Integer bookingId) throws Exception {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();


            BookingModel foundBooking = em.find(BookingModel.class, bookingId);
            em.remove(foundBooking);
            em.getTransaction().commit();
            em.close();

            return foundBooking;
        } catch (HibernateException e) {
            throw new Exception("Repository action failed");
        }
    }


    public static void main(String[] args) throws Exception {
        BookingRepository bookingRepository = new BookingRepository();
        UserRepository userRepository = new UserRepository();
        PackRepository packRepository = new PackRepository();
        UserModel user = userRepository.findUser(8);
        PackModel pack = packRepository.findPack(20);
        BookingDTO bookingDTO = new BookingDTO(user, pack);
        BookingModel book = bookingRepository.insertBooking(bookingDTO);
//        BookingModel book = bookingRepository.findBooking(3);
//        UserModel pack = userRepository.updateUser(1, user);
//        BookingModel book = bookingRepository.removeBooking(6);
//        book = bookingRepository.removeBooking(6);
        System.out.println(book.toString());
    }
}
