package com.app.forumchat.models.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private ERole name;


    public Role() {
    }

    public Role(ERole role) {
        this.name = role;
    }
}
