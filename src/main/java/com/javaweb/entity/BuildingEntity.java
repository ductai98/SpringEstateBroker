package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="building")
public class BuildingEntity extends BaseEntity {
    private static final long serialVersionUID = 7593564374959286532L;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private Long level;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "emptyarea")
    private Long emptyArea;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "brokeragefee")
    private Double brokerageFee;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphonenumber")
    private String managerPhoneNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "buildingrenttype",
                joinColumns = @JoinColumn(name = "buildingid", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "renttypeid", nullable = false))
    private Set<RentTypeEntity> rentTypes;

    @OneToMany(mappedBy="building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false)
    )
    private List<UserEntity> staffs = new ArrayList<>();

    public Long getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(Long emptyArea) {
        this.emptyArea = emptyArea;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhone) {
        this.managerPhoneNumber = managerPhone;
    }

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public Set<RentTypeEntity> getRentTypes() {
        return rentTypes;
    }

    public void setRentTypes(Set<RentTypeEntity> rentTypes) {
        this.rentTypes = rentTypes;
    }
}

