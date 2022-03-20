package com.a1.a1.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "destination", schema = "sd-a1", catalog = "")
public class DestinationModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "destinationByDestinationId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PackModel> packsById;

    public DestinationModel(String name) {
        this.name = name;
    }

    public DestinationModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DestinationModel() {
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
        DestinationModel that = (DestinationModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DestinationModel{" +
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
}
