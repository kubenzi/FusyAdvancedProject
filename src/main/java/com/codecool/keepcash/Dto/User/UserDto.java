package com.codecool.keepcash.Dto.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private Long id;
    private String username;

    @JsonCreator
    public UserDto(@JsonProperty ("id") Long id,
                   @JsonProperty ("username") String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
