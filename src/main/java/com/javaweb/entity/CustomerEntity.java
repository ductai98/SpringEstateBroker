package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity {

    @Column(name="fullname")
    private String fullName;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="demand")
    private String demand;

    @Column(name="status", columnDefinition = "int(11) default '1'")
    private Long status = 1L;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "assignmentcustomer",
                joinColumns = @JoinColumn(name = "customerid", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffs;

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
