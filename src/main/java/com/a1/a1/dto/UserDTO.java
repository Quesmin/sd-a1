package com.a1.a1.dto;

import com.a1.a1.model.AgencyModel;

public class UserDTO {
    private String email;
    private String password;
    private AgencyModel agency;

    public UserDTO(String email, String password, AgencyModel agencyModel) {
        this.email = email;
        this.password = password;
        this.agency = agencyModel;
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AgencyModel getAgency() {
        return agency;
    }

    public void setAgency(AgencyModel agency) {
        this.agency = agency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
