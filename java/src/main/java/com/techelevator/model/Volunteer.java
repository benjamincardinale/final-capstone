package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Volunteer {
    private long id;
    @NotNull(message = "Must have a first name.")
    private String firstName;
    @NotNull(message = "Must have a last name.")
    private String lastName;
    @NotNull(message = "Must have an email address.")
    private String emailAddress;
    private int status;

    public Volunteer() {

    }

    public Volunteer(long id, String firstName, String lastName, String emailAddress, int status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.status = status;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
