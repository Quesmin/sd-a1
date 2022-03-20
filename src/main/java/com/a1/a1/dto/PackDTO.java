package com.a1.a1.dto;

import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;

import java.sql.Date;

public class PackDTO {

    private DestinationModel destination;
    private AgencyModel agency;
    private String name;
    private Integer price;
    private Date startDate;
    private Date endDate;
    private String extraDetails;
    private Integer maxSlots;

    public PackDTO(String name, Integer price, Date startDate, Date endDate, String extraDetails, Integer maxSlots, DestinationModel destination, AgencyModel agency) {
        this.destination = destination;
        this.agency = agency;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.maxSlots = maxSlots;
    }

    public AgencyModel getAgency() {
        return agency;
    }

    public void setAgency(AgencyModel agency) {
        this.agency = agency;
    }

    public DestinationModel getDestination() {
        return destination;
    }

    public void setDestination(DestinationModel destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public Integer getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(Integer maxSlots) {
        this.maxSlots = maxSlots;
    }

}
