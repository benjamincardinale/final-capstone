package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordDTO {

    @NotEmpty
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
