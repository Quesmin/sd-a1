package com.a1.a1.dto;

import com.a1.a1.model.PackModel;
import com.a1.a1.model.UserModel;

public class BookingDTO {
    private UserModel user;
    private PackModel pack;

    public BookingDTO(UserModel user, PackModel pack) {
        this.user = user;
        this.pack = pack;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PackModel getPack() {
        return pack;
    }

    public void setPack(PackModel pack) {
        this.pack = pack;
    }
}
