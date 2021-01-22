package com.codecool.keepcash.Dto.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewEmailDto {

    private String newEmail;
    private String oldEmail;

    public NewEmailDto() {
    }

    @JsonCreator
    public NewEmailDto(@JsonProperty("newEmail") String newEmail,
                       @JsonProperty("oldEmail") String oldEmail) {
                this.newEmail = newEmail;
                this.oldEmail = oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }
}
