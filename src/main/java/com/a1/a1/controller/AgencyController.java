package com.a1.a1.controller;

import com.a1.a1.dto.PackDTO;
import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;
import com.a1.a1.model.PackModel;
import com.a1.a1.repository.AgencyRepository;
import com.a1.a1.repository.DestinationRepository;
import com.a1.a1.service.DestinationService;
import com.a1.a1.service.PackService;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class AgencyController {
    DestinationService destinationService;
    PackService packService;

    public AgencyController(){
        destinationService = new DestinationService();
        packService = new PackService();
    }

    public DestinationModel addVacationDestination(String name) throws Exception {
            return destinationService.createDestination(name);

    }

    public DestinationModel updateDestination(Integer destId, String name) throws Exception {
            return destinationService.changeDestination(destId, name);

    }

    public DestinationModel deleteDestinatioin(Integer destId){
        try{
            return destinationService.removeDestination(destId);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public PackModel addPack(PackDTO pack) throws Exception {
            return packService.createPack(pack);
    }

    public PackModel changePack(Integer packId, PackDTO packDTO) throws Exception {
            return packService.updatePack(packId, packDTO);
    }
    public PackModel removePack(Integer packId){
        try{
            return packService.deletePack(packId);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public List<PackModel> viewPackages(AgencyModel agency){
        try{
            return packService.getPackages(agency);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        AgencyController agencyController = new AgencyController();

        DestinationModel destination = new DestinationRepository().findDestination(3);
        AgencyModel agency = new AgencyRepository().findAgency(1);

        PackDTO packDTO = new PackDTO("asd", 123, Date.valueOf("2021-03-15"), Date.valueOf("2021-03-17"), "asd", 10, destination, agency);

        PackModel result = agencyController.addPack(packDTO);
        System.out.println(result);
//        List<PackModel> result = agencyController.viewPackages(agency);
    }
}
