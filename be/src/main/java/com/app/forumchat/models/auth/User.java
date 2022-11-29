package com.app.forumchat.models.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String last_name;

    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<Role>();

    private boolean active; //TODO aggiornare metodi registrati per settare la variabile + metodo di admin per disattivare un user

    private int points;
}
