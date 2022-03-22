package com.a1.a1.service;

import com.a1.a1.model.DestinationModel;
import com.a1.a1.repository.DestinationRepository;

public class DestinationService {

    DestinationRepository destinationRepository;

    public DestinationService(){
        destinationRepository = new DestinationRepository();
    }
    public DestinationModel createDestination(String name) throws Exception {
        if (name.equals("") || name == null){
            throw new Exception("Invalid name");
        }

        return destinationRepository.insertDestination(name);
    }

    public DestinationModel removeDestination(Integer destId) throws Exception {
        if (destId == null){
            throw new Exception("Invalid name");
        }

        return destinationRepository.removeDestination(destId);
    }

    public DestinationModel changeDestination(Integer destId, String name) throws Exception {
        if (name == null || name.equals("")){
            throw new Exception("Invalid name");
        }

        return destinationRepository.updateDestination(destId, name);
    }


}
