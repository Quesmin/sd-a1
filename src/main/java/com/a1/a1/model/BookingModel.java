package com.a1.a1.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "booking", schema = "sd-a1")
public class BookingModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pack_id", referencedColumnName = "id", nullable = false)
    private PackModel packByPackId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserModel userByUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingModel that = (BookingModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookingModel{" +
                "id=" + id +
                '}';
    }

    public PackModel getPackByPackId() {
        return packByPackId;
    }

    public void setPackByPackId(PackModel packByPackId) {
        this.packByPackId = packByPackId;
    }

    public UserModel getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserModel userByUserId) {
        this.userByUserId = userByUserId;
    }
}
