package com.javaweb.model.request;

import java.util.List;

public class AssignRequest {

    private Long id;
    private List<Long> staffIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }
}
