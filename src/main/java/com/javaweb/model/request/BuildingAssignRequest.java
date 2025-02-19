package com.javaweb.model.request;

import java.util.List;

public class BuildingAssignRequest {

    private String buildingId;
    private List<Long> staffs;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public List<Long> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Long> staffs) {
        this.staffs = staffs;
    }
}
