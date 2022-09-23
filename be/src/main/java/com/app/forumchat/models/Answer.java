package com.app.forumchat.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String body;

    @OneToOne
    private Topic topic;

    @OneToOne
    private User user;
}
