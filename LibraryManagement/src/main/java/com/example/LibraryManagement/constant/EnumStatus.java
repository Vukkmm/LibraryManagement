package com.example.LibraryManagement.constant;

import lombok.Getter;

@Getter
public enum EnumStatus {
    NOT_YET_RETURNED("NOT YET RETURNED"),
    RETURNED("RETURNED"),
    RETURN_OVERDUE("RETURN OVERDUE");

    private final String status;

    EnumStatus(String status) {
        this.status = status;
    }

//    public String getStatus() {
//        return status;
//    }



}
