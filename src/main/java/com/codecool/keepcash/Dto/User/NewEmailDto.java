package com.codecool.keepcash.Dto.User;

public class NewEmailDto {

    private String newEmail;

    public NewEmailDto() {
    }

    public NewEmailDto(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
