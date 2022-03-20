package com.a1.a1.service;

import com.a1.a1.dto.BookingDTO;
import com.a1.a1.dto.PackFilterDTO;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.*;
import com.a1.a1.repository.*;

import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private PackRepository packRepository;
    private BookingRepository bookingRepository;
    private AgencyRepository agencyRepository;
    private DestinationRepository destinationRepository;

    public UserService(){
        this.userRepository = new UserRepository();
        this.packRepository = new PackRepository();
        this.bookingRepository = new BookingRepository();
        this.agencyRepository = new AgencyRepository();
        this.destinationRepository = new DestinationRepository();
    }

    public UserModel createUSer(UserDTO newUser) throws Exception {
        String email = newUser.getEmail();
        String password = newUser.getPassword();

        if (email.equals("") || email == null){
            throw new Exception("Invalid email");
        }

        if(password.equals("") || password == null){

            throw new Exception("Invalid password");
        }

        return userRepository.insertUser(newUser);
    }

    public UserModel authenticate(UserDTO user) throws Exception {
        return userRepository.findUserByEmailAndPassword(user);
    }

    public AgencyModel getAssociatedAgency(UserDTO user) throws Exception {
        if(user.getAgency() != null){
            return agencyRepository.findAgency(user.getAgency().getId());
        }
        return null;
    }

    public List<PackModel> getAllAvailablePackages() throws Exception {
        List<PackModel> allPackages = packRepository.findAllPack();
        List<PackModel> temp = allPackages
                .stream()
                .filter(e -> e.getBookingsById().size() < e.getMaxSlots())
                .toList();
        return temp;
    }

    public List<PackModel> getFilteredPackages(PackFilterDTO filter) throws Exception {
        return packRepository.filterPackages(filter);
    }

    public List<DestinationModel> getAllDestinations() throws Exception {
        return destinationRepository.findAllDestination();
    }

    public BookingModel createBooking(BookingDTO booking) throws Exception {
        return bookingRepository.insertBooking(booking);
    }

    public List<BookingModel> getBookedVacations(Integer userId) throws Exception {
        return bookingRepository.findAllBookings()
                .stream()
                .filter(e -> e.getUserByUserId().getId().equals(userId))
                .toList();
    }
}
