package com.a1.a1.service;

import com.a1.a1.dto.BookingDTO;
import com.a1.a1.dto.PackFilterDTO;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.*;
import com.a1.a1.repository.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.regex.Pattern;

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

        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        newUser.setPassword(hashedPassword);

        return userRepository.insertUser(newUser);
    }

    public UserModel authenticate(UserDTO user) throws Exception {
        String regexPattern = "^(.+)@(\\S+)$";
        String email = user.getEmail();
        String password = user.getPassword();

        if(email.equals("") || !Pattern.compile(regexPattern).matcher(email).matches()){
            throw new Exception("Invalid email!");
        }

        UserModel foundUser = userRepository.findUserByEmail(user);
        if(password.equals("") || !BCrypt.checkpw(password, foundUser.getPassword())){
            throw new Exception("Invalid password!");
        }

        return foundUser;
    }

    public UserModel getUser(Integer userId) throws Exception {
        return userRepository.findUser(userId);
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
        if(filter.getMinPrice() != null && filter.getMinPrice() < 0){
            throw new Exception("Negative price");
        }
        if(filter.getMaxPrice() != null && filter.getMaxPrice() < 0){
            throw new Exception("Negative price");
        }
        if(filter.getMaxPrice() != null && filter.getMinPrice() != null && filter.getMaxPrice() < filter.getMinPrice()){
            throw new Exception("Invalid price range!");
        }

        if(filter.getStartDate() != null && filter.getEndDate() != null && filter.getStartDate().getTime() > filter.getEndDate().getTime()){
            throw new Exception("Invalid date range!");
        }

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
