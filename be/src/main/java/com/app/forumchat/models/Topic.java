package com.app.forumchat.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String head_title;

    private String body;

    @OneToMany(mappedBy="topic")
    private List<Answer> answer;

    @OneToOne
    private User user;
}
