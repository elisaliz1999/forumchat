package com.app.forumchat.services;

import com.app.forumchat.models.Answer;
import com.app.forumchat.models.Topic;
import com.app.forumchat.models.auth.User;
import com.app.forumchat.repos.AnswerRepo;
import com.app.forumchat.repos.TopicRepo;
import com.app.forumchat.repos.auth.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepo tr;
    @Autowired
    private UserRepo ur;
    @Autowired
    private AnswerRepo ar;

    public Topic saveTopic(Topic topic, long id){
        Optional<User> user = ur.findById(id);
        if(user.isPresent()){
            topic.setUser(user.get());
            return tr.save(topic);
        }
        return null;
    }

    public Answer saveAnswer(Answer answer, long idT, long idU){
        Optional<User> user = ur.findById(idU);
        Optional<Topic> topic = tr.findById(idT);
        if(user.isPresent() && topic.isPresent()){
            answer.setTopic(topic.get());
            answer.setUser(user.get());
            return ar.save(answer);
        }
        return null;
    }
}
