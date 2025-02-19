package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name="rentarea")
public class RentAreaEntity extends BaseEntity {

    @Column(name="value")
    private Long value;

    @ManyToOne
    @JoinColumn(name="buildingid", nullable = false)
    private BuildingEntity building;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
