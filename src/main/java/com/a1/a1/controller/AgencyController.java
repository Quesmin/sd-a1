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
import java.util.List;

public class AgencyController {
    DestinationService destinationService;
    PackService packService;

    public AgencyController(){
        destinationService = new DestinationService();
        packService = new PackService();
    }

    public DestinationModel addVacationDestination(String name){
        try{
            return destinationService.createDestination(name);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public PackModel addPack(PackDTO pack){
        try{
            return packService.createPack(pack);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public PackModel changePack(Integer packId, PackDTO packDTO){
        try{
            return packService.updatePack(packId, packDTO);
        } catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
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

        DestinationModel destination = new DestinationRepository().findDestination(10);
        AgencyModel agency = new AgencyRepository().findAgency(2);
        List<PackModel> result = agencyController.viewPackages(agency);
    }
}
