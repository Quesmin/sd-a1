package com.a1.a1.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "sd-a1", catalog = "")
public class UserModel {
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "userByUserId")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BookingModel> bookingsById;
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id")
    private AgencyModel agencyByAgencyId;

    public UserModel( Integer id, String email, String password) {
        this.email = email;
        this.password = password;
        this.id = id;
    }


    public UserModel() {
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
        UserModel userModel = (UserModel) o;
        return Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(id, userModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, id);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public List<BookingModel> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(List<BookingModel> bookingsById) {
        this.bookingsById = bookingsById;
    }

    public AgencyModel getAgencyByAgencyId() {
        return agencyByAgencyId;
    }

    public void setAgencyByAgencyId(AgencyModel agencyByAgencyId) {
        this.agencyByAgencyId = agencyByAgencyId;
    }
}
