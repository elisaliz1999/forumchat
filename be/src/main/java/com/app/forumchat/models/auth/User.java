package com.app.forumchat.models.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    //@NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    //@NotBlank
    private String name;

    //@NotBlank
    private String last_name;

    //@NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    private int points;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

}
