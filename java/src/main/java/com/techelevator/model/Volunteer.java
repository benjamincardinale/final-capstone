package com.techelevator.model;

import javax.validation.constraints.NotBlank;

public class Volunteer {
    private long id;
    @NotBlank(message = "Must have a first name.")
    private String firstName;
    @NotBlank(message = "Must have a last name.")
    private String lastName;
    @NotBlank(message = "Must have a username that is not blank.")
    private String username;
    @NotBlank(message = "Must have an email address.")
    private String emailAddress;
    @NotBlank(message = "Must be either true or false")
    private boolean adoptedBefore;
    @NotBlank(message = "Must be either true or false")
    private boolean hasCertifications;
    private String status;

    public Volunteer() {

    }

    public Volunteer(long id, String firstName, String lastName, String username,String emailAddress, String status, boolean adoptedBefore, boolean hasCertifications) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.emailAddress = emailAddress;
        this.status = status;
        this.adoptedBefore = adoptedBefore;
        this.hasCertifications = hasCertifications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAdoptedBefore() {
        return adoptedBefore;
    }

    public void setAdoptedBefore(boolean adoptedBefore) {
        this.adoptedBefore = adoptedBefore;
    }

    public boolean isHasCertifications() {
        return hasCertifications;
    }

    public void setHasCertifications(boolean hasCertifications) {
        this.hasCertifications = hasCertifications;
    }
}
