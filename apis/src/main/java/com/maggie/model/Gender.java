package com.maggie.model;

public enum Gender {

    FEMALE("Female"),
    MALE("Male");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
