package com.a1.a1.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pack", schema = "sd-a1")
public class PackModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "extra_details")
    private String extraDetails;
    @Basic
    @Column(name = "max_slots")
    private Integer maxSlots;
    @OneToMany(mappedBy = "packByPackId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BookingModel> bookingsById;
    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id", nullable = false)
    private DestinationModel destinationByDestinationId;
    @ManyToOne
    @JoinColumn(name = "agency_id", referencedColumnName = "id", nullable = false)
    private AgencyModel agencyByAgencyId;

    public PackModel(Integer id, String name, Integer price, Date startDate, Date endDate, String extraDetails, Integer maxSlots) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.maxSlots = maxSlots;
    }

    public PackModel() {
    }

    public String getStatus(){
        if(bookingsById.size() == maxSlots){
            return "BOOKED";
        }

        if(bookingsById.isEmpty()){
            return "NOT_BOOKED";
        }

        return "IN_PROGRESS";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackModel packModel = (PackModel) o;
        return Objects.equals(id, packModel.id) && Objects.equals(name, packModel.name) && Objects.equals(price, packModel.price) && Objects.equals(startDate, packModel.startDate) && Objects.equals(endDate, packModel.endDate) && Objects.equals(extraDetails, packModel.extraDetails) && Objects.equals(maxSlots, packModel.maxSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, startDate, endDate, extraDetails, maxSlots);
    }

    @Override
    public String toString() {
        return "PackModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", extraDetails='" + extraDetails + '\'' +
                ", maxSlots=" + maxSlots +
                '}';
    }

    public List<BookingModel> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(List<BookingModel> bookingsById) {
        this.bookingsById = bookingsById;
    }

    public DestinationModel getDestinationByDestinationId() {
        return destinationByDestinationId;
    }

    public void setDestinationByDestinationId(DestinationModel destinationByDestinationId) {
        this.destinationByDestinationId = destinationByDestinationId;
    }

    public AgencyModel getAgencyByAgencyId() {
        return agencyByAgencyId;
    }

    public void setAgencyByAgencyId(AgencyModel agencyByAgencyId) {
        this.agencyByAgencyId = agencyByAgencyId;
    }
}
