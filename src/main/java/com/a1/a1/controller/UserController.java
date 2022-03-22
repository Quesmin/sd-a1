package com.a1.a1.controller;

import com.a1.a1.dto.BookingDTO;
import com.a1.a1.dto.PackFilterDTO;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.*;
import com.a1.a1.repository.PackRepository;
import com.a1.a1.repository.UserRepository;
import com.a1.a1.service.UserService;

import java.util.Arrays;
import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(){
        userService = new UserService();
    }

    public UserModel registerUser(UserDTO newUser){
        try{
            return userService.createUSer(newUser);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public UserModel loginUser(UserDTO user){
        try{
            return userService.authenticate(user);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public UserModel getUser(Integer userId){
        try{
            return userService.getUser(userId);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public List<PackModel> viewAllAvailablePackages(){
        try{
            return userService.getAllAvailablePackages();
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public List<PackModel> filterPackages(PackFilterDTO filter){
        try{
            return userService.getFilteredPackages(filter);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public List<DestinationModel> getAllDestinations(){
        try{
            return userService.getAllDestinations();
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public BookingModel bookVacationPackage(BookingDTO bookingDTO){
        try{
            return userService.createBooking(bookingDTO);
        } catch (Exception e){
            Arrays.stream(e.getStackTrace()).forEach(err -> System.out.println(err));
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public List<BookingModel> viewAllBookedVacations(Integer userId){
        try{
            return userService.getBookedVacations(userId);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public AgencyModel getAssociatedAgency(UserDTO userDTO) throws Exception {
        return userService.getAssociatedAgency(userDTO);
    }

    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        UserModel user = new UserRepository().findUser(8);
        PackModel pack = new PackRepository().findPack(20);
        BookingDTO bookingDTO = new BookingDTO(user, pack);

//        UserModel result = userController.loginUser(user);

//        PackFilterDTO filter = new PackFilterDTO();
//        filter.setDestination(dest);
//        filter.setMinPrice(10);
//        filter.setMaxPrice(2000);
//        filter.setStartDate(Date.valueOf("2021-02-22"));
//        filter.setEndDate(Date.valueOf("2021-03-10"));
//        List<BookingModel> result = userController.viewAllBookedVacations(3);
        BookingModel result = userController.bookVacationPackage(bookingDTO);
        System.out.println(result.toString());
    }
}
