package com.app.forumchat.controllers;

import com.app.forumchat.models.Answer;
import com.app.forumchat.models.Topic;
import com.app.forumchat.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService ts;

    @PostMapping("/save/{id}")
    public Topic save(@RequestBody Topic topic, @PathVariable long id){
        return ts.saveTopic(topic, id);
    }

    @PostMapping("/save/answ/{idu}/{idt}")
    public Answer save(@RequestBody Answer answer, @PathVariable long idu, @PathVariable long idt){
        return ts.saveAnswer(answer, idt, idt);
    }
}
