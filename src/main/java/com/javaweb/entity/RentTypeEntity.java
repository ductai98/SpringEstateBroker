package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="renttype")
public class RentTypeEntity extends BaseEntity {

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "rentTypes", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildings;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }
}
