package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordDTO {

    @NotEmpty
    private String newPassword;

    public ChangePasswordDTO() {}

    public ChangePasswordDTO(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
