package com.codecool.keepcash.Dto.Authentication;

public class UserPasswordChangeDto {

    private String currentPassword;
    private String newPassword;

    public UserPasswordChangeDto() {
    }

    public UserPasswordChangeDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
