package com.a1.a1.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "agency", schema = "sd-a1")
public class AgencyModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "agencyByAgencyId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PackModel> packsById;
    @OneToMany(mappedBy = "agencyByAgencyId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserModel> usersById;

    public AgencyModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AgencyModel(String name) {
        this.name = name;
    }

    public AgencyModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyModel that = (AgencyModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AgencyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<PackModel> getPacksById() {
        return packsById;
    }

    public void setPacksById(List<PackModel> packsById) {
        this.packsById = packsById;
    }

    public List<UserModel> getUsersById() {
        return usersById;
    }

    public void setUsersById(List<UserModel> usersById) {
        this.usersById = usersById;
    }
}
