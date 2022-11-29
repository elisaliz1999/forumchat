package com.app.forumchat.models.auth;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDTO {

    private String jwt;

    private long id;

    private String username;

    private String email;

    private List<String> roles;

    public JwtResponseDTO(String jwt, long id, String username, String email, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
