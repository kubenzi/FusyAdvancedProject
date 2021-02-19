package com.codecool.keepcash.Dto.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewEmailDto {

    private String newEmail;
    private String newEmailConfirmed;
    private String oldEmail;
    private String password;

    public NewEmailDto() {
    }

    @JsonCreator
    public NewEmailDto(@JsonProperty("newEmail") String newEmail,
                       @JsonProperty("newEmailConfirmed") String newEmailConfirmed,
                       @JsonProperty("oldEmail") String oldEmail,
                       @JsonProperty("password") String password){
                this.newEmail = newEmail;
                this.newEmailConfirmed = newEmailConfirmed;
                this.oldEmail = oldEmail;
                this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewEmailConfirmed() {
        return newEmailConfirmed;
    }

    public void setNewEmailConfirmed(String newEmailConfirmed) {
        this.newEmailConfirmed = newEmailConfirmed;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
