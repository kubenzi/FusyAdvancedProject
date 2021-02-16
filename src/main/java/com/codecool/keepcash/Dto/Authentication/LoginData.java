package com.codecool.keepcash.Dto.Authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginData {

    private Long userId;
    private String username;
    private String sessionId;

    @JsonCreator
    public LoginData(@JsonProperty ("userId") Long userId,
                     @JsonProperty ("username") String username,
                     @JsonProperty ("sessionId") String sessionId) {
        this.userId = userId;
        this.username = username;
        this.sessionId = sessionId;
    }

    public LoginData() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
