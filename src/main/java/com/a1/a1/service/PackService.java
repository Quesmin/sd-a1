package com.a1.a1.service;

import com.a1.a1.dto.PackDTO;
import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.PackModel;
import com.a1.a1.repository.PackRepository;

import java.util.List;

public class PackService {

    static final Integer MAX_SLOTS = 99999;
    static final Integer MAX_PRICE = 99999;
    PackRepository packRepository;

    public PackService(){
        packRepository = new PackRepository();
    }
    public PackModel createPack(PackDTO packDTO) throws Exception {
        if (packDTO.getName().equals("") || packDTO.getName() == null){
            throw new Exception("Invalid name");
        }

        if (packDTO.getDestination() == null){
            throw new Exception("Invalid destination");
        }
        if (packDTO.getAgency() == null){
            throw new Exception("Invalid agency");
        }

        if (packDTO.getStartDate() == null || packDTO.getEndDate() == null || packDTO.getStartDate().getTime() > packDTO.getEndDate().getTime()){
            throw new Exception("Invalid date interval");
        }

        if (packDTO.getMaxSlots() < 0 || packDTO.getMaxSlots() > MAX_SLOTS || packDTO.getMaxSlots() == null){
            throw new Exception("Invalid max slots input");
        }

        if (packDTO.getPrice() < 0 || packDTO.getPrice() > MAX_PRICE || packDTO.getPrice() == null){
            throw new Exception("Invalid price input");
        }

        return packRepository.insertPack(packDTO);
    }

    public PackModel updatePack(Integer packId, PackDTO packDTO) throws Exception {
        return packRepository.updatePack(packId, packDTO);
    }
    public PackModel deletePack(Integer packId) throws Exception {
        return packRepository.removePack(packId);
    }

    public List<PackModel> getPackages(AgencyModel agency) throws Exception {
        return packRepository.findPackagesByAgency(agency);
    }
}
