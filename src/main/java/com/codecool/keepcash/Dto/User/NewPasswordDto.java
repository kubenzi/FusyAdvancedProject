package com.codecool.keepcash.Dto.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPasswordDto {

    private String newPassword;
    private String newPasswordConfirmed;
    private String oldPassword;

    public NewPasswordDto() {
    }

    @JsonCreator
    public NewPasswordDto(@JsonProperty("newPassword") String newPassword,
                          @JsonProperty("newPasswordConfirmed") String newPasswordConfirmed,
                          @JsonProperty("oldPassword") String oldPassword) {
        this.newPassword = newPassword;
        this.newPasswordConfirmed = newPasswordConfirmed;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirmed() {
        return newPasswordConfirmed;
    }

    public void setNewPasswordConfirmed(String newPasswordConfirmed) {
        this.newPasswordConfirmed = newPasswordConfirmed;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
