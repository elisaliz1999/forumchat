package com.app.forumchat.services;

import com.app.forumchat.models.Topic;
import com.app.forumchat.repos.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepo tr;

    public Topic saveTopic(Topic topic){

    }
}
