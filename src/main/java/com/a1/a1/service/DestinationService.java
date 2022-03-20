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


}
