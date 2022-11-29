package com.app.forumchat.models.auth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private ERole name;

    public Role() {
    }

    public Role(ERole role) {
        this.name = role;
    }

}
