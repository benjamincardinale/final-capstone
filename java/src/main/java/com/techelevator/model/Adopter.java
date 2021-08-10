package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Adopter {
    @NotNull(message = "Must have a pet id")
    private long petId;
    private long adopterId;
    @NotEmpty(message = "Must have an adoption date")
    private String adoptionDate;
    @NotEmpty(message = "Must have a name")
    private String name;

    public Adopter () {}

    public Adopter(long petId, long adopterId, String adoptionDate, String name) {
        this.petId = petId;
        this.adopterId = adopterId;
        this.adoptionDate = adoptionDate;
        this.name = name;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(long adopterId) {
        this.adopterId = adopterId;
    }

    public String getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(String adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
